package br.equals.DataFlowManager;

import br.equals.DataFlowManager.api.CardMonitor;
import br.equals.DataFlowManager.api.DirMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DataFlowManagerApplication {
	private static CardMonitor cardMonitor;
	private static DirMonitor dirMonitor;
	@Autowired
	public DataFlowManagerApplication(CardMonitor cardMonitor, DirMonitor dirMonitor) {
		DataFlowManagerApplication.cardMonitor = cardMonitor;
		DataFlowManagerApplication.dirMonitor = dirMonitor;
	}

	@PostConstruct
	public void criarLink(){
		dirMonitor.addObserver(cardMonitor);
		new Thread(dirMonitor).start();
	}

	public static void main(String[] args) {
		SpringApplication.run(DataFlowManagerApplication.class, args);
	}
}
