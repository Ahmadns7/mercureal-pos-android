# MercuReal POS — Android shell

Native Android app (WebView shell) for MercuReal POS. Data lives in the app's own private DOM storage, so clearing Chrome or other browsers can never wipe sales, stock, credit or expenses. Cloud sync (Backup tab) still works over the network.

## Behaviour

- Online: loads https://mercureal-pos.vercel.app
- Offline: loads the bundled copy in `app/src/main/assets/web/` (rebuild and re-copy `dist/` from the web repo on each release)
- Back button navigates the WebView history instead of closing the app
- External links (non-vercel.app) open outside the shell

## Build

Open this folder in Android Studio (or run `./gradlew assembleDebug`). Debug APK installs directly on the Tecno Spark 10c.

## Web repo

https://github.com/Ahmadns7/mercureal-pos
