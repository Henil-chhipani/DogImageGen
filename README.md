# Dog Image Generator App

An Android app built with Jetpack Compose that generates random dog images using the [Dog CEO API](https://dog.ceo/dog-api/). The app allows users to generate random dog images, save them in a cache, and view or clear their recently generated images.

---

## Features

1. **Home Screen:**
    - Navigation buttons to access the "Generate Dogs" and "Recently Generated Dogs" screens.

2. **Generate Dogs Screen:**
    - A "Generate!" button that fetches a random dog image from the API.
    - Displays the fetched image.
    - Saves the image in an LRU cache (up to 20 most recent images).

3. **Recently Generated Dogs Screen:**
    - A scrollable gallery of the images saved in the cache.
    - A "Clear Dogs" button to clear the cache and gallery.

---

## Tech Stack

- **Programming Language:** Kotlin
- **UI Framework:** Jetpack Compose
- **Dependency Injection:** Hilt
- **Networking:** Retrofit
- **JSON Parsing:** Moshi
- **State Management:** StateFlows with ViewModel
- **Local Storage:** DataStore Preferences

---

## Screen Recording

[Link](https://drive.google.com/file/d/16Dm0FepfAu_mcw23xLIUjId5KAeNkvWh/view?usp=drive_link)

---

## Setup and Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
