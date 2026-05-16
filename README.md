# ماینو بانک | Mineo Bank

![Kotlin](https://img.shields.io/badge/Kotlin-2.1.0-7F52FF?style=flat-square&logo=kotlin)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-2024.12-4285F4?style=flat-square&logo=jetpackcompose)
![Material3](https://img.shields.io/badge/Material3-Latest-757575?style=flat-square&logo=material-design)
![Min SDK](https://img.shields.io/badge/Min%20SDK-26-brightgreen?style=flat-square&logo=android)
![License](https://img.shields.io/badge/License-MIT-blue?style=flat-square)
![Build](https://img.shields.io/github/actions/workflow/status/masoodkhademi/neobank-customer-android/build.yml?style=flat-square&label=Build)

> **بانکداری هوشمند، تجربه‌ای نو** | Smart Banking, New Experience

A modern Persian RTL neobank Android app built with the latest **Jetpack Compose**, **Material Design 3**, and **Kotlin 2.1**. ماینو بانک delivers a seamless digital banking experience across 5 core screens backed by 9 dedicated microservices.

---

## ✨ Features

- 🎨 **Full RTL Persian UI** — native right-to-left layout for Farsi speakers
- 💳 **Digital Wallet** — real-time balance, QR code payments, and instant transfers
- 📊 **Transaction History** — live feed of all account activity with icons
- 🛒 **Services Hub** — 9 banking services: bill pay, top-up, insurance, loans, gold & more
- 📈 **Smart Investment** — digital gold and investment fund products
- 👤 **Profile & Security** — identity verification, security settings, support tickets
- 🔐 **JWT Authentication** — secure token-based login with auto-refresh
- 🌐 **9 Microservices** — modular backend integration across auth, wallet, bank account, and more
- 🚀 **Jetpack Compose** — fully declarative UI with `StateFlow` and `collectAsStateWithLifecycle`
- 🎯 **Material Design 3** — dynamic theming, dark mode support, M3 components

---

## 📱 Screenshots

| Splash | Login | Home | Wallet | Services |
|:---:|:---:|:---:|:---:|:---:|
| _(coming soon)_ | _(coming soon)_ | _(coming soon)_ | _(coming soon)_ | _(coming soon)_ |

| Investment | Profile |
|:---:|:---:|
| _(coming soon)_ | _(coming soon)_ |

---

## 🏗 Architecture

```
MVVM + Clean Architecture
────────────────────────────────────────────
  UI Layer        →  Compose Screens + ViewModels (StateFlow)
  Domain Layer    →  Repositories + Use Cases
  Data Layer      →  Retrofit API Services + TokenManager
────────────────────────────────────────────
  Single Activity (MainActivity)
  Compose Navigation (NavHost + BottomBar)
```

- **Single Activity** with Compose `NavHost` replacing all Fragments
- **`AndroidViewModel`** with `StateFlow` for reactive state management
- **Repository pattern** isolating network calls from UI
- **`TokenManager`** (SharedPreferences) for secure JWT storage
- **`AuthInterceptor`** auto-injects `Bearer` token on every request

---

## 🛠 Tech Stack

| Technology | Version | Purpose |
|---|---|---|
| **Kotlin** | 2.1.0 | Primary language |
| **Jetpack Compose** | BOM 2024.12.01 | Declarative UI framework |
| **Material Design 3** | via BOM | Design system & components |
| **Compose Navigation** | 2.8.5 | In-app screen navigation |
| **ViewModel + StateFlow** | 2.8.7 | Reactive state management |
| **Activity Compose** | 1.9.3 | `setContent` + edge-to-edge |
| **Retrofit** | 2.9.0 | Type-safe HTTP client |
| **OkHttp** | 4.12.0 | HTTP networking layer |
| **Gson Converter** | 2.9.0 | JSON serialization |
| **Coroutines** | 1.9.0 | Async & concurrent operations |
| **AGP** | 8.5.2 | Android Gradle Plugin |
| **Gradle** | 8.7 | Build system |

---

## 🌐 Backend Microservices

| Service | Port | Description |
|---|---|---|
| **Auth Service** | `5003` | User login, registration & JWT token management |
| **Base Data** | `5001` | Core application reference data |
| **Wallet Service** | `5005` | Digital wallet, balance & fund transfers |
| **Service Hub** | `5006` | Financial services aggregator (top-up, bills, etc.) |
| **Support Service** | `5007` | Customer support & ticketing system |
| **Logs Service** | `5009` | Application event logging |
| **Media Service** | `5010` | File upload & image management |
| **Mini App Service** | `5011` | Embedded mini-application platform |
| **Bank Account** | `5012` | Bank account & card management |

Base URL for emulator: `http://10.0.2.2:<port>/`

---

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or later
- JDK 17+
- Android SDK 35
- A running instance of the backend microservices

### Clone & Open

```bash
git clone https://github.com/masoodkhademi/neobank-customer-android.git
cd neobank-customer-android
```

Open the project in Android Studio and let Gradle sync.

### Configure API

Edit `app/src/main/java/com/mineobank/app/network/ApiConfig.kt`:

```kotlin
const val AUTH_BASE_URL         = "http://10.0.2.2:5003/"
const val WALLET_BASE_URL       = "http://10.0.2.2:5005/"
const val BANK_ACCOUNT_BASE_URL = "http://10.0.2.2:5012/"
// ... update remaining service URLs
```

For a physical device, replace `10.0.2.2` with your machine's local IP address.

### Build & Run

```bash
./gradlew assembleDebug
# or run directly from Android Studio
```

---

## 📂 Project Structure

```
app/src/main/
├── java/com/mineobank/app/
│   ├── MainActivity.kt              # Single entry point, splash logic
│   ├── data/
│   │   ├── local/
│   │   │   └── TokenManager.kt      # JWT storage (SharedPreferences)
│   │   ├── model/                   # Data models (auth, wallet, bank account…)
│   │   ├── remote/
│   │   │   └── ApiResult.kt         # Sealed result wrapper (Success/Error/Loading)
│   │   └── repository/              # AuthRepository, WalletRepository, UserRepository
│   ├── network/
│   │   ├── ApiConfig.kt             # Service base URLs
│   │   ├── NetworkClient.kt         # Retrofit factory
│   │   ├── AuthInterceptor.kt       # Bearer token injection
│   │   └── *ApiService.kt           # Retrofit interfaces per service
│   └── ui/
│       ├── theme/
│       │   ├── Color.kt             # Mineo brand palette
│       │   ├── Theme.kt             # Material3 theme + RTL direction
│       │   └── Type.kt              # Typography scale
│       ├── navigation/
│       │   ├── Screen.kt            # Route sealed class
│       │   └── MineoNavigation.kt   # NavHost + scaffold wiring
│       ├── components/
│       │   ├── MineoBottomBar.kt    # Dark navy bottom navigation
│       │   └── MineoTopBar.kt       # Reusable top app bar
│       ├── auth/
│       │   ├── LoginScreen.kt       # Compose login screen
│       │   └── LoginViewModel.kt    # LoginUiState + StateFlow
│       ├── home/
│       │   ├── HomeScreen.kt        # Balance card + transactions
│       │   └── HomeViewModel.kt     # AndroidViewModel + StateFlow
│       ├── wallet/
│       │   └── WalletScreen.kt      # Wallet card + QR + transfers
│       ├── services/
│       │   └── ServicesScreen.kt    # 3×3 service grid
│       ├── investment/
│       │   └── InvestmentScreen.kt  # Gold & fund investment cards
│       └── profile/
│           └── ProfileScreen.kt     # User info + menu + logout
└── res/
    ├── drawable/                    # Vector icons & backgrounds
    ├── mipmap-*/                    # Launcher icons
    ├── values/
    │   └── strings.xml              # Persian string resources
    └── xml/
        └── network_security_config.xml
```

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Commit your changes: `git commit -m 'feat: add your feature'`
4. Push to the branch: `git push origin feature/your-feature`
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License.

---

<div align="center">
  Built with ❤️ for the Persian-speaking community
  <br/>
  <strong>ماینو بانک</strong> — بانکداری هوشمند، تجربه‌ای نو
</div>
