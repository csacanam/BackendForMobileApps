# Backend For Mobile Apps

This project is created to show:

1. How to build a backend using Spark Java
2. How to consume information from Spark Java (IaaS)
2. How to consume information using Parse (BaaS)

The app is a simple list that load the information from two kinds of backend: Spark web service (IaaS) and Parse (BaaS)

<img src="https://dl.dropboxusercontent.com/u/60646493/backendformobile.gif" width="350" height="600" />

## Run web service - Spark Java

- Open simplelistbackend in your favorite Java IDE (NetBeans, Eclipse or IntelliJ)
- Run the project
- Check that the service is running in your machine in the port 4567. You can check this in the browser with the URL http://localhost:4567/list. It should show a JSON Array.

## Configure Spark access in Android

- Open SimpleListAndroid in Android Studio
- Go to MainActivity.java and change the URL in the doInBackground method of HttpRequestTask inner class. For example, my URL will be: http://172.30.162.239:4567/list

## Configure Parse access in Android

- Create an app in [Parse](http://parse.com/)
- Import the files that are in [Parse folder](/Parse)
- Change the tokens in App.java for your Application ID and your Client Key (The're created by Parse)

## Run app

- Run your app in Android Studio
- Check that the list change when you pressed both buttons (Parse and Slack). Each button access the information from a different source
