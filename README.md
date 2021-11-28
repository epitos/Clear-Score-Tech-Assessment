Frameworks 

‘org.koin:koin-android-viewmodel:2.1.6’: For Dependency Injection
‘org.koin:koin-android:2.1.6’: For Dependency Injection
‘com.squareup.retrofit2:retrofit:2.8.1’: Http Client Wrapper
‘com.squareup.retrofit2:converter-moshi:2.6.2’ Handle JSON
‘com.squareup.okhttp3:okhttp:4.7.2’: Http Client
‘com.squareup.okhttp3:logging-interceptor:4.7.2’: To see the logs of the http requests 
‘org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0’: To handle multithreading
‘org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0’: To handle multithreading
‘com.jackandphantom.android:circularprogressbar:1.2.0’: To display the circular view for the donut view.
“com.squareup.okhttp3:mockwebserver:4.6.0”: Mock API responses.

Architecture

The project is structured using the MVVM pattern.

Classes

Application

CreditInfoApplication: Used to start Koin and set the modules.

Model

CoachingSummary: Holds Coaching Summary data.

CreditInfoReport: Holds Credit Info Report data.

Response: Holds the JSON response.

Repository

CreditInfoRepository: Handles data operations, such as getting the data from the server.

DI -> Module

AppModule: Provides the dependencies for the Retrofit and WebService.

RepositoryModule: Provides the dependency for the CreditInfoRepository.

ViewModelModule: Provides the dependency for the CreditInfoViewModel.

UI

View

CreditInfoActivity: UI to display Donut View and credit score.

Viewmodel

CreditInfoViewModel: Holds and manages the data.

Utils

Const: Holds base url, endpoints and other constant variables.

NetworkHelper: Declares a method to check if device is connected to the internet.

Percentage: Contains a function to convert the clear score to a percentage.

Resource: Contains data and status about loading data.

Status: Declares data status.


Interface

Data

WebService: Contains endpoint.

Android Test

CreditInfoViewModelTest: Tests API call 
