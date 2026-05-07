# 🎵 MoodTunes — Android App

AI-powered mood-based music recommendations. Tell us how you feel, Claude picks the perfect playlist.

## Tech Stack

- **Kotlin** + **Jetpack Compose** — 100% declarative UI
- **MVVM + Clean Architecture** — Repository, ViewModel, UseCase layers
- **Hilt** — Dependency injection
- **Retrofit + OkHttp** — Networking to MoodTunes API
- **Room** — Local history and favourites storage
- **DataStore** — User preferences
- **Coil** — Image loading (album art)
- **Claude AI** (via backend API) — Music recommendations

## Screens

| Screen | Description |
|--------|-------------|
| Onboarding | 3-slide intro with swipe navigation |
| Sign In / Sign Up | Auth screens matching Figma design |
| Home | Mood scroll + hero banner + recent sessions |
| Mood Picker | 2×4 mood grid + free-text input |
| Recommendations | AI song cards with Spotify + YouTube buttons |
| Now Playing | Full-screen player + Claude AI reason view |
| History | Date-grouped past mood sessions |
| Favourites | Saved tracks with quick-play buttons |

## Setup

### 1. Clone the repo
```bash
git clone https://github.com/YOUR_USERNAME/moodtunes-android
cd moodtunes-android
```

### 2. Create `local.properties`
```
sdk.dir=C:\Users\YOUR_USERNAME\AppData\Local\Android\Sdk
```

### 3. Set your API URL in `app/build.gradle.kts`
```kotlin
buildConfigField("String", "API_BASE_URL", "\"https://your-moodtunes-api.up.railway.app/\"")
buildConfigField("String", "API_KEY", "\"your-internal-api-key\"")
```

### 4. Open in Android Studio → Run on device or emulator

## Architecture

```
app/
├── data/
│   ├── model/          # Data classes + Room entities
│   ├── remote/         # Retrofit API service
│   ├── local/          # Room DB + DAOs
│   └── repository/     # MoodTunesRepository
├── domain/
│   └── usecase/        # Business logic use cases
├── presentation/
│   ├── home/           # HomeScreen + ViewModel
│   ├── moodpicker/     # MoodPickerScreen + ViewModel
│   ├── recommendations/# RecommendationsScreen + ViewModel
│   ├── player/         # NowPlayingScreen + ViewModel
│   ├── history/        # HistoryScreen + ViewModel
│   ├── favourites/     # FavouritesScreen + ViewModel
│   ├── auth/           # SignIn + SignUp screens
│   ├── onboarding/     # Onboarding slides
│   └── components/     # Shared composables
├── di/                 # Hilt modules
├── navigation/         # NavGraph + Screen routes
└── ui/theme/           # Theme, colors, typography
```

## Backend

This app requires the [MoodTunes API](https://github.com/YOUR_USERNAME/moodtunes-api) to be deployed.
See that repo for setup instructions.

## License

MIT
