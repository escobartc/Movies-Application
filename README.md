# Movies-Application
## Project structure and tools
A movies MVVM application with a single activity and multiple fragments that contain compose views.
- DaggerHilt was used for dependency injection
- Retrofit was used for the network calls
- Room was implemented to store information locally
- Kotlin coroutines were used to handle asynchronous tasks
- Jetpack Compose was used for some views
- For the use of the map and location reading Compose Google Map was used

## Screen 1:

 Profile screen, including information about the most popular user with a user-friendly and intuitive UI, where reviews made by the user and images can be viewed.
 The information shown in the screen is not saved in a local database as this functionality was implemented in the screen 2. 
 
| Profile | Loading | Error |
|---------|---------|-------|
| <img src="https://github.com/user-attachments/assets/4b00a2d4-953b-47ab-a1b5-0532c32b6845" width="500" />| <img src="https://github.com/user-attachments/assets/f98bd940-066d-4ddf-9941-846b0beb4506" width="500" /> | <img src="https://github.com/user-attachments/assets/e8216c0d-3476-4544-8ec8-798031ab1c47" width="500" /> |

## Screen 2:
Load the list of all movies with a user-friendly and intuitive UI, separated into the most popular movies, the highest-rated, and the best recommendations.

This screen loads the list of all movies (popular, top rated and recommended) and also stores the movies in a local database implemented with room.

The services used for this screen were the following:

- Popular movies: /movie/popular 
- Top rated movies: /movie/top_rated
- Recommended movies: /movie/{movie_id}/recommendations

The recommendations were based on one of the popular movies

| Movies | 
|--------|
| <img src="https://github.com/user-attachments/assets/10c42a2e-8a6b-4e96-94d8-6402a3549323" width="500" />|


## Screen 3:
Fetch data from Firebase Console (Cloud Firestore) and display locations on a map, additionally showing the storage date using a user-friendly and intuitive UI. Add the device's location to a Firebase Console to persist (Cloud Firestore) every 5 minutes and notify the user via NotificationCompat.

For this screen a foreground service was implemented in order to collect the user location every 5 minutes (if the permissions are granted) and also there is a google map in jetpack compose which has markers for each one of the existing positions in the firebase firestore DB.
| Map | Notification | Firestore  |
|-----|--------------|------------|
|  <img src="https://github.com/user-attachments/assets/79dfd343-f2f4-41d7-8445-9dbb64342efd" width="500" />|  <img src="https://github.com/user-attachments/assets/84fb473e-b732-47db-b4b2-a9d04c7df5d6" width="500" />| <img src="https://github.com/user-attachments/assets/ae1686af-433b-4ce1-872f-f50bef635e7f" />| 

## Screen 4:
Capture or select one or more images from the device gallery and upload them to Firebase Storage. Currently is selecting the image from the gallery but is not saving it properly in firebase storage, so it displays an error message. 
| Image Picker | Error |
|--------| -------|
|  <img src="https://github.com/user-attachments/assets/d24257f9-4d85-4252-b300-fba8e9df0124" width="500" /> | <img src="https://github.com/user-attachments/assets/9cbfcafb-1f34-4739-9d0e-c92b65612625" width="500" /> | 



## App missing aspects or improvements:
- Adding more states for loading components or empty components
- Adding camera activity to take a photo and upload it to firebase storage
- Adding unit tests to the components created
