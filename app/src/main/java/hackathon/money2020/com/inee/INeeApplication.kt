package hackathon.money2020.com.inee

import android.app.Application

/**
 * Created by JH on 10/20/18.
 */
class INeeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/HelveticaNeue.ttf")

    }
}