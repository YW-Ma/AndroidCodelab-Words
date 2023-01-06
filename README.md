# Words App

This folder contains the source code for the Words app codelab.


# Introduction
Words app allows you to select a letter and use Intents to navigate to an Activity that
presents a number of words starting with that letter. Each word can be looked up via a web search.

Words app contains a scrollable list of 26 letters A to Z in a RecyclerView. The orientation
of the RecyclerView can be changed between a vertical list or a grid of items.

The app demonstrates the use of Intents in two ways:
* to navigate inside an app by specifying an explicit destination, and,
* allowing Android to service the Intent using the apps and resources present on the device.

# Pre-requisites
* Experience with Kotlin syntax.
* Able to create an Activity.
* Able to create a RecyclerView and supply it with data.

# Getting Started
1. Install Android Studio, if you don't already have it.
2. Download the sample.
3. Import the sample into Android Studio.
4. Build and run the sample.

# Note:
* The lifecycle of a fragment differs from the lifecycle of an activity, with view setup occurring in onViewCreated(), rather than onCreateView().
  * In onCreate(), handle argument
  * In onCreateView(), handle inflate the layout (_binding), and return _binding.root
* A FragmentContainerView is used to embed fragments in other activities and can manage navigation between fragments.
  * It can also be used as NavHostFragment in MainActivity, to set title in ActionBar and support `onSupportNavigateUp`.
* How to use navigation component:
  * Setting the `navGraph` attribute of a `FragmentContainerView`. It allows us to navigate between fragments within an activity. (manage fragments associate to an activity)
  * The NavGraph editor:
    * add navigation actions
    * specify arguments for fragment
    * add label for fragment
  * Using `SafeArgs` plugins to ensure type safety with arguments.
  * In code:
    * use `FooFragmentDirections.actionName(argument)` to create an action
    * using `navController.navigate(action)` to trigger the navigate
    * these can be inside a button's `onClickListener`
* Use cases for fragments
  * multiple tabs within same activity
    * different device, reuse
  * Single-Activity Architecture with Navigation Component
    * Activities should not own a View hierarchy <-- should be handle by fragment
    * only respond to event that switch fragments