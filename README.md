## Uni - Mobile Test
*  App initially retrieves data from local **.json** file in **Assets** folder.
*  List items on Content Feed Screen are clickable to view details on the Add Content Screen.
*  Click on the floating action button on the Content Feed Screen to display the Add Content Screen to add a new list item.
*  Newly added items can be seen at the top of the list on Content Feed Screen once saved or uploaded from Add Content Screen.

### Limitations
1. [Android Studio](https://developer.android.com/studio#downloads) 3.2 and above
2. Minimum Android SDK version 21
3. Kotlin Version 1.3.72 and above

### Installation 
*  Clone repository with android Studio
*  Accept notification to change Android SDK path to your sdk local path
*  Make sure you are on the master branch


### App Architecture (Model-View-View-Model/I)
![MVVM/I Architecture](/Images/architecture.png)


### Implementation Features
*  Recommended [MVVM App Architecture](https://developer.android.com/jetpack/guide) for android development with repository patten.
*  Implementation of [Android Navigation library](https://developer.android.com/guide/navigation)
*  [Data binding](https://developer.android.com/jetpack/androidx/releases/databinding) of components in layouts and data sources. 
*  Offline support as data is saved to sql light database using [ROOM](https://developer.android.com/topic/libraries/architecture/room)
*  Android UI test with [Espresso](https://developer.android.com/training/testing/espresso)
 
### Caveats
*  Images used in the app are intentional to follow request not to include company name.
