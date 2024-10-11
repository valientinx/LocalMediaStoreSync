# LocalMediaStoreSync

My Home Cloud
Purpose, - Backup / Sync files (media or non media from device to storage server)

    Read media from device by using:
        - MediaStore
        - The File API. (classic way)
        - Native libraries, such as fopen().

    Connection mechanism
        - server / client using Ktor (Selected)
        - or P2P ? (device to device)

TODO:
~~1. Get phone images.~~
~~2. Add permission system~~
-Add pagination for the list
-Add a jump to top FAB (scroll back to top)
-Build Ktor connection to desktop app. 
    - https://github.com/valientinx/LocalMediaStoreSyncServerTestClient
    - https://github.com/valientinx/LocalMediaStoreSyncServer
-Add data layer (Repository architecture)

Training materials:
https://developer.android.com/courses/pathways/jetpack-compose-for-android-developers-1
https://developer.android.com/codelabs/jetpack-compose-layouts#4

Kotlin:
https://kotlinlang.org/docs/getting-started.html
