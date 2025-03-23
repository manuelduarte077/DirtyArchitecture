# Rick and Morty
This is a simple Android app that displays a list of characters from the popular TV show "Rick and Morty". The app uses a clean architecture approach and the MVI (Model-View-Intent) pattern.


## Modules
The app's architecture consists of the following modules:
- `app` - The main application module.
- `core`
  - `ui_kit` - A module for shared UI components and styles.
- `domain` - The domain module, containing the use cases and repositories.
- `data` - The data module, responsible for fetching data from the API and managing local storage.
- `presentation`
  - `character-list` - The module responsible for the character list screen.
  - `character-details` - A module that handles the character detail screen, providing a detailed view of a selected character's information.

## Libraries
| Library | Description |
| --- | --- |
| Retrofit | HTTP client for making API requests |
| OkHttp | Networking library used by Retrofit |
| Kotlinx Serialization | JSON serialization/deserialization |
| Coil | Image loading and caching library |
| Hilt | Dependency injection framework |
| Hilt Navigation Compose | Navigation integration for Hilt |
| Compose | Google's modern UI toolkit for Android |
| Compose Navigation | Navigation library for Compose |
