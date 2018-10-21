package hackathon.money2020.com.inee

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mikepenz.materialdrawer.DrawerBuilder

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))

        DrawerBuilder().withActivity(this).build()

        supportFragmentManager.beginTransaction().add(R.id.container, RecipientFragment()).commit()
    }

}
