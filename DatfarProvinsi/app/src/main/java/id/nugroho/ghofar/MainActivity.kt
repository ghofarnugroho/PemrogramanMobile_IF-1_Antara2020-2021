package id.nugroho.ghofar

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var recyclerviewItemAdapter: RecyclerviewItemAdapter? = null
    private var itemsList: MutableList<Province>? = null
    private val url = "https://dev.farizdotid.com/api/daerahindonesia/provinsi"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        itemsList = ArrayList()
        recyclerView = findViewById<View>(R.id.recycleView) as RecyclerView
        recyclerviewItemAdapter = RecyclerviewItemAdapter(itemsList as ArrayList<Province>)
        recyclerView!!.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = recyclerviewItemAdapter
        data
    }

    private val data: Unit
        private get() {
            val stringRequest = StringRequest(url, { response ->
                try {
                    val array = JSONObject(response)
                    val obj = array["provinsi"]
                    val respon = obj.toString()
                    val province = JSONArray(respon)
                    for (i in 0 until province.length()) {
                        try {
                            val jsonObject = province.getJSONObject(i)
                            val provinsi = Province()
                            provinsi.name = jsonObject.getString("nama")
                            itemsList!!.add(provinsi)
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                        recyclerviewItemAdapter!!.notifyDataSetChanged()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }) { }
            val requestQueue = Volley.newRequestQueue(this)
            requestQueue.add(stringRequest)
        }
}