package cubex.mahesh.wifi_august7am

import android.content.Context
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var wManager = applicationContext.
          getSystemService(Context.WIFI_SERVICE) as WifiManager
        var state:Int = wManager.wifiState
        if(state==0 || state==1){
            s1.isChecked = false
        }else if(state==2 || state==3){
            s1.isChecked = true
        }
        s1.setOnCheckedChangeListener { compoundButton, b ->
            wManager.setWifiEnabled(b)
            var vib = getSystemService(Context.VIBRATOR_SERVICE)
                    as Vibrator
            vib.vibrate(10000)
        }

        gwd.setOnClickListener {
         var list:List<ScanResult> =    wManager.scanResults
          var temp_list : MutableList<String>   = mutableListOf()
            for(device in list){
                temp_list.add(device.SSID+"\n"+device.frequency)
            }
            var myadapter = ArrayAdapter<String>(
                    this@MainActivity,
                    android.R.layout.simple_list_item_single_choice,
                    temp_list)
            lview.adapter = myadapter
        }  // gwd

        gpd.setOnClickListener {
            var list:List<WifiConfiguration> =    wManager.configuredNetworks
            var temp_list : MutableList<String>   = mutableListOf()
            for(device in list){
                temp_list.add(device.SSID+"\n"+device.status)
            }
            var myadapter = ArrayAdapter<String>(
                    this@MainActivity,
                    android.R.layout.simple_list_item_single_choice,
                    temp_list)
            lview.adapter = myadapter
        }


    } // onCreate
}
