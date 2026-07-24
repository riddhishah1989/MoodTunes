# 🎵 MoodTunes — Android App

> “Spotify knows what you listened to. MoodTunes knows how you feel.”

MoodTunes is an Android app for mood-based music recommendations. Users can
describe how they feel, receive a tailored set of songs from the MoodTunes
backend, and discover matching tracks on Spotify and YouTube.

> [!NOTE]
> MoodTunes is under active development. Authentication and password-recovery
> flows are currently connected to the app navigation. The data and domain
> layers already support recommendations, favourites, a mood journal, insights,
> playlist sharing, and music-provider searches; their remaining screens and
> navigation are still being completed.

## Tech Stack

- **Kotlin 2.0** + **Jetpack Compose Material 3** — declarative Android UI
- **MVVM + Clean Architecture** — presentation, domain, and data layers
- **Hilt** — dependency injection
- **Retrofit + OkHttp + Gson** — REST API networking and JSON conversion
- **Kotlin Coroutines + Flow** — asynchronous work and observable UI state
- **Preferences DataStore** — JWT, user profile, and app preferences
- **Coil** — remote image loading
- **Lottie Compose** — animations
- **Navigation Compose** — screen routing and navigation arguments
- **Spotify + YouTube** (through the backend) — track discovery links

The app targets Android API 35, supports API 26 and newer, and uses Java 17.

## Features and Screens

| Area | Description | Status |
|---|---|---|
| Splash | Checks the saved session and chooses the next destination | Implemented |
| Sign In | Authenticates a user and stores the returned session | Implemented |
| Sign Up | Creates an account with profile information | Implemented |
| Forgot Password | Requests a password-reset OTP by email | Implemented |
| Verify OTP | Verifies the reset code and supports resending it | Implemented |
| Reset Password | Sets a new password after OTP verification | Implemented |
| Change Password | Changes the password for an authenticated user | Implemented |
| Home | Main signed-in destination | In progress |
| Mood Recommendations | Select moods and genres and request personalized songs | API/domain ready |
| Favourites | Add, list, check, remove, or clear saved tracks | API/domain ready |
| Mood Journal | Create, read, delete, or clear mood entries | API/domain ready |
| Insights | Retrieve mood and listening insights for a date range | API/domain ready |
| Playlist Sharing | Generate and retrieve shared playlists | API/domain ready |
| Spotify / YouTube Search | Find provider matches for recommended songs | API/domain ready |

## Setup

### 1. Clone the repository

```bash
git clone https://github.com/riddhishah1989/MoodTunes.git
cd MoodTunes
```

### 2. Configure the Android SDK

Android Studio normally creates `local.properties` automatically. If needed,
create it in the project root:

```properties
# Windows (escape backslashes in a .properties file)
sdk.dir=C\:\\Users\\YOUR_USERNAME\\AppData\\Local\\Android\\Sdk
```

On macOS or Linux:

```properties
sdk.dir=/Users/YOUR_USERNAME/Library/Android/sdk
```

### 3. Configure the backend

Set the REST API base URL and internal API key in
`app/build.gradle.kts`:

```kotlin
defaultConfig {
    buildConfigField(
        "String",
        "API_BASE_URL",
        "\"https://your-moodtunes-api.example.com/\""
    )
    buildConfigField(
        "String",
        "API_KEY",
        "\"your-internal-api-key\""
    )
}
```

The base URL must end with `/` because it is passed to Retrofit. Do not commit
production secrets to a public repository; for production, load them from a
local Gradle property, environment variable, or your CI secret store.

### 4. Build and run

Open the repository in Android Studio, allow Gradle to sync, select an emulator
or physical device running Android 8.0 (API 26) or newer, and click **Run**.

You can also build a debug APK from the command line:

```bash
./gradlew assembleDebug
```

On Windows PowerShell:

```powershell
.\gradlew.bat assembleDebug
```

## Architecture

```text
app/src/main/java/com/moodtunes/app/
├── data/
│   ├── local/              # Preferences DataStore
│   ├── mapper/             # API-to-domain mappings
│   ├── remote/
│   │   ├── request/        # REST request models
│   │   ├── response/       # REST response models
│   │   └── MoodTunesApiService.kt
│   └── repository/         # Repository implementation
├── domain/
│   ├── local/              # Static app data
│   ├── model/              # App-facing models
│   ├── repository/         # Repository contract
│   ├── result/             # Success/error result types
│   └── usecase/            # Auth, recommendation, journal,
│                           # favourites, insights, and sharing use cases
├── presentation/
│   ├── auth/               # Login, registration, and forgot password
│   ├── changepassword/     # Authenticated password update
│   ├── components/         # Shared Compose UI
│   ├── home/               # Home UI
│   ├── resetpassword/      # New-password flow
│   ├── splash/             # Launch/session routing
│   ├── state/              # Common ViewModel and UI-state helpers
│   └── verifyotp/          # OTP entry and verification
├── di/                     # Hilt dependency modules
├── navigation/             # Navigation graph and routes
├── ui/theme/               # Compose theme
├── MainActivity.kt
└── MoodTunesApp.kt
```

The presentation layer calls focused domain use cases. Those use cases depend
on `IMoodTunesRepository`, whose implementation coordinates the Retrofit API
and local preferences. Hilt provides these dependencies to ViewModels.

## Backend API

MoodTunes expects a REST backend exposing the following groups:

- `/api/v1/auth` — sign-up, sign-in, profile, password, OTP, and token refresh
- `/api/v1/moods` and `/api/v1/genres` — recommendation inputs
- `/api/v1/recommendations` — mood-based song recommendations
- `/api/v1/favourites` — saved songs
- `/api/v1/journal` — mood journal entries
- `/api/v1/insights` — mood and listening insights
- `/api/v1/share` — shared playlists
- `/api/v1/spotify/search` and `/api/v1/youtube/search` — provider lookup

Authenticated calls send a bearer token from DataStore. Every request also
sends the configured `x-api-key` header.

## License

No license file is currently included. Add a `LICENSE` file before distributing
the project under an open-source license.
