# My ZeXro V1 — Android WebView APK

Proyek Android membungkus situs online: https://project-zexro-v1.netlify.app/

## Build gratis melalui GitHub dari HP Android
1. Ekstrak ZIP ini.
2. Buat repository baru di https://github.com/new.
3. Upload ISI folder proyek ke root repository (settings.gradle, build.gradle, app/, .github/).
4. Buka tab Actions dan aktifkan workflow bila diminta.
5. Pilih workflow **Build My ZeXro APK** > **Run workflow**.
6. Setelah workflow selesai, buka run yang sukses, lalu unduh Artifact **My-ZeXro-V1-debug-apk**.
7. Ekstrak artifact dan pasang `app-debug.apk`. Android mungkin meminta izin instal aplikasi dari sumber ini.

## Catatan
- Ini WebView online; hosting situs tetap dibutuhkan.
- Login/Owner Panel tetap memakai integrasi Supabase pada situs.
- Tidak ada Supabase service_role key di APK.
- File chooser didukung. Tautan unduhan dialihkan ke aplikasi/browser Android; uji sebelum digunakan.
- Jika alamat situs berubah, ubah HOME_URL dan allowlist host di MainActivity.java.
- Debug APK untuk pengujian, bukan rilis Play Store.
- Fitur web tertentu dapat berbeda di WebView. Uji login, owner, upload, clipboard, video, unduhan, dan notification center.
