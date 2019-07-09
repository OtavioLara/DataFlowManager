# Data Flow Manager(DFM)

* The backend of DFM, made with SpringBoot in a restful comunication with front-end [dfm-frontend](https://github.com/OtavioLara/dfm-frontend).

* "with a great amount of data, comes great data managers." - Uncle Google

---

### Container Postgres command:

* docker run -i -t -p 5432:5432 --restart=always --name teste -e POSTGRES_DB=equals -e POSTGRES_PASSWORD=equals2019 -d postgres

### Maven

* How this API is a maven project you must to solve the dependencies before. I recommend Intellij Idea to do it.

### DFM backend apllication

* The DFM keeps watching its *receive* folder created at the root of the project. 

* When a some other **"imaginary tool"** created for the download management put a new file in the *receive* folder, the file is read and added to the postgress data base.

* There is a @RestController implemented to receive a get from http and send the data from postgres in json format. 

* The server is started in localhost:8090 and only wait a get in localhost:8090/api/v1/card
