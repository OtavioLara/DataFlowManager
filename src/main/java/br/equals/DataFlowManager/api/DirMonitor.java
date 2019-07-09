package br.equals.DataFlowManager.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

@Service
public class DirMonitor implements Runnable, Observable{

    private List<Observer> observers = new ArrayList<>();
    private Set<String> currentPaths = new HashSet<>();
    private final String DIR;

    @Autowired
    public DirMonitor(){
        DIR = System.getProperty("user.dir")+"/receive";
        if(!new File(DIR).mkdirs()){
            System.out.println("Directory already exists "+DIR);
        }else{
            System.out.println("Creating path: "+DIR);
        }
    }

    private void startMonitoring(){
        WatchService watcher = null;
        try {
            watcher = FileSystems.getDefault().newWatchService();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Path dir = Path.of(DIR);
        while(true) {
            WatchKey key = null;
            try {
                key = dir.register(watcher,
                        ENTRY_CREATE);
            } catch (IOException IOe) {
                IOe.printStackTrace();
            }

            for (WatchEvent<?> event: key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                if (kind == OVERFLOW) {
                    continue;
                }
                WatchEvent<Path> ev = (WatchEvent<Path>)event;
                if(ev.kind() == ENTRY_CREATE){
                    String filePath = dir.resolve(ev.context()).toString();
                    if(currentPaths.add(filePath)) {
                        notifyObservers(filePath);
                        System.out.println("File was added: " + dir.resolve(ev.context()));
                    }
                }
            }

            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
    }
    @Override
    public void run() {
        this.startMonitoring();
    }

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void notifyObservers(String path) {
        observers.forEach(observer -> observer.update(path));
    }
}