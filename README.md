# 🎵 MoodTunes — Android App

> **"Spotify knows what you listened to. MoodTunes knows how you feel."**

AI-powered mood-based music recommendation app. Describe how you feel — Claude picks the perfect playlist, enriched with Spotify and YouTube links.

---

## What makes MoodTunes different

| Feature | Spotify | Apple Music | MoodTunes |
|---|---|---|---|
| Music recommendations | Based on history | Based on history | Based on **current emotion** |
| Natural language input | ❌ | ❌ | ✅ "just had a fight with my friend" |
| Mood journal | ❌ | ❌ | ✅ |
| AI personal insights | ❌ | ❌ | ✅ |
| "Why this song fits your mood" | ❌ | ❌ | ✅ |
| Share a mood playlist | ❌ | ❌ | ✅ |
| Spotify + YouTube in one app | ❌ | ❌ | ✅ |

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Kotlin 1.9+ |
| UI | Jetpack Compose |
| Architecture | MVVM + Clean Architecture |
| DI | Hilt |
| Networking | Retrofit 2 + OkHttp |
| Local Storage | Room DB + DataStore |
| Image Loading | Coil |
| Async | Kotlin Coroutines + Flow |
| Navigation | Jetpack Navigation Compose |
| AI | Claude API via MoodTunes backend |
| Music | Spotify Web API + YouTube Data API |

---

## Screens

| Screen | Description |
|---|---|
| Splash | App intro with tagline |
| Onboarding | 3-slide swipe intro explaining MoodTunes |
| Sign Up | Register with name, email, password |
| Sign In | Login with JWT token |
| Home | Hero banner + mood scroll + recent sessions |
| Mood Picker | 2×4 mood grid + free-text input + genre filter |
| Recommendations | AI song cards with Spotify + YouTube buttons |
| Now Playing | Full-screen player + Claude AI reason + prev/next |
| History | Date-grouped past mood sessions |
| Favourites | Saved tracks with quick-play buttons |
| Mood Journal | Log your mood with note + star rating |
| Insights | AI-powered mood + music pattern analysis |
| Profile | Edit profile, change password, linked services |

---

## Setup

### 1. Clone the repo
```bash
git clone https://github.com/riddhishah1989/moodtunes-android
cd moodtunes-android
```

### 2. Create `local.properties`
```
sdk.dir=C:\Users\YOUR_USERNAME\AppData\Local\Android\Sdk
```

### 3. Set your API URL in `app/build.gradle.kts`
```kotlin
defaultConfig {
    buildConfigField("String", "API_BASE_URL", "\"https://your-moodtunes-api.up.railway.app/\"")
}
```

### 4. Open in Android Studio
File → Open → select the `MoodTunes` folder → Sync Gradle → Run

---

## Architecture

```
app/
├── data/
│   ├── model/              # Data classes, Room entities, API models
│   ├── remote/             # Retrofit MoodTunesApiService
│   ├── local/              # Room DB + DAOs (sessions, favourites, journal)
│   └── repository/         # MoodTunesRepository
├── domain/
│   └── usecase/            # Business logic use cases
├── presentation/
│   ├── home/               # HomeScreen + HomeViewModel
│   ├── moodpicker/         # MoodPickerScreen + MoodPickerViewModel
│   ├── recommendations/    # RecommendationsScreen + ViewModel
│   ├── player/             # NowPlayingScreen + NowPlayingViewModel
│   ├── history/            # HistoryScreen + HistoryViewModel
│   ├── favourites/         # FavouritesScreen + FavouritesViewModel
│   ├── journal/            # JournalScreen + JournalViewModel
│   ├── insights/           # InsightsScreen + InsightsViewModel
│   ├── profile/            # ProfileScreen + ProfileViewModel
│   ├── auth/               # SignInScreen + SignUpScreen
│   ├── onboarding/         # OnboardingScreen
│   └── components/         # Shared composables (SongCard, MoodChip, etc.)
├── di/                     # Hilt modules (NetworkModule, DatabaseModule)
├── navigation/             # NavGraph + Screen sealed class
└── ui/theme/               # Theme, colors, typography, shapes
```

---

## API Endpoints Used

| Screen | Endpoint |
|---|---|
| Sign Up | `POST /api/v1/auth/signup` |
| Sign In | `POST /api/v1/auth/signin` |
| Profile | `GET /api/v1/auth/me` |
| Update Profile | `PUT /api/v1/auth/profile` |
| Change Password | `PUT /api/v1/auth/password` |
| Delete Account | `DELETE /api/v1/auth/account` |
| Mood Picker | `GET /api/v1/moods` |
| Genre Picker | `GET /api/v1/genres` |
| Recommendations | `POST /api/v1/recommendations` |
| History | `GET /api/v1/history` |
| Single Session | `GET /api/v1/history/:id` |
| Delete Session | `DELETE /api/v1/history/:id` |
| Favourites | `GET /api/v1/favourites` |
| Add Favourite | `POST /api/v1/favourites` |
| Check Favourite | `GET /api/v1/favourites/:songId/check` |
| Remove Favourite | `DELETE /api/v1/favourites/:songId` |
| Journal | `GET /api/v1/journal` |
| Add Journal Entry | `POST /api/v1/journal` |
| Delete Journal Entry | `DELETE /api/v1/journal/:id` |
| Insights | `GET /api/v1/insights` |
| Share Playlist | `POST /api/v1/share` |
| Spotify Search | `GET /api/v1/spotify/search?q=` |
| YouTube Search | `GET /api/v1/youtube/search?q=` |

---

## Color Palette

| Token | Hex | Usage |
|---|---|---|
| Background | `#08080F` | App background |
| Surface | `#0F0F1E` | Cards |
| Primary | `#6C63FF` | Accent, buttons, active states |
| Spotify | `#1DB954` | Spotify play buttons |
| YouTube | `#C4302B` | YouTube play buttons |
| Happy | `#FFD93D` | Mood chip accent |
| Sad | `#5B8FD4` | Mood chip accent |
| Energetic | `#FF6B6B` | Mood chip accent |
| Calm | `#5DD68A` | Mood chip accent |
| Romantic | `#FF63A5` | Mood chip accent |
| Focused | `#4D96FF` | Mood chip accent |
| Angry | `#FF4444` | Mood chip accent |
| Anxious | `#C77DFF` | Mood chip accent |

---

## Backend

This app requires the **MoodTunes API** to be running.

👉 [moodtunes-api](https://github.com/riddhishah1989/moodtunes-api) — Node.js + Express + MongoDB

---

## Related

| Repo | Description |
|---|---|
| [moodtunes-api](https://github.com/riddhishah1989/moodtunes-api) | Backend REST API |

---

## License

MIT