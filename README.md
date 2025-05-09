# OnyxDeliveryService

OnyxDeliveryService is a delivery management application that provides functionality for managing delivery orders, user authentication, and real-time updates. It is built with modern technologies like Jetpack Compose, Retrofit, Room, Koin for dependency injection, and uses Kotlin for efficient and clean code.

## Features

- User Login and Authentication
- View Delivery Bills by states (news, other )
- Real-Time Data Sync with Backend
- Smooth UI with Jetpack Compose
- Dependency Injection with Koin
- Local Data Storage with Room

## Technologies Used

- **Kotlin**: Main language used for building the app.
- **Jetpack Compose**: UI framework for building native Android UIs.
- **Room**: Local database for persistent storage.
- **Retrofit**: Networking library for RESTful APIs.
- **Koin**: Dependency injection framework.


## System archeticture
this app  is based on separate by feature , but is still in packages to reduce implementation time
but it is ready to be multi-module

every feature has its [data, domain and ui) package

there is a shared package(module) to be shared with another modules
## Setup Instructions

### Prerequisites

Ensure you have the following installed on your system:

- [Android Studio](https://developer.android.com/studio)
- [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

### Clone the repository

```bash
git clone https://github.com/AhmedMHassaan/OnyxDeliveryService.git
cd OnyxDeliveryService

```
### Build the app
Open the project in Android Studio and sync Gradle files. Then you can build and run the project on an emulator or physical devic


### Run the project
Open the MainActivity.kt to start the app.
You can now start interacting with the app.

### License
This project is licensed under the MIT License - see the LICENSE file for details.


