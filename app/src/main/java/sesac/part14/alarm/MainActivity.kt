package sesac.part14.alarm

import android.content.Context
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vibButton = findViewById<Button>(R.id.vibration)
        val beepButton = findViewById<Button>(R.id.beep)

        vibButton.setOnClickListener {
            val vibrator = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
                val vibManager = this.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                vibManager.defaultVibrator
            }else {
                getSystemService(VIBRATOR_SERVICE) as Vibrator
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))

            }else {
                vibrator.vibrate(500)
            }
        }

        beepButton.setOnClickListener {
            val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val ringtone = RingtoneManager.getRingtone(applicationContext, uri)
            ringtone.play()
        }
    }
}