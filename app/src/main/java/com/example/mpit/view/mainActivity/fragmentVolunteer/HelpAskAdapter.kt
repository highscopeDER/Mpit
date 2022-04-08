package com.example.mpit.view.mainActivity.fragmentVolunteer

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.graphics.createBitmap
import androidx.recyclerview.widget.RecyclerView
import com.example.mpit.R
import kotlinx.coroutines.NonDisposableHandle.parent

class HelpAskAdapter : RecyclerView.Adapter<HelpAskAdapter.ViewHolder>() {

    private val names = arrayOf("Нина Алексеева", "Инна Иванова", "Михаил Павлов", "Мичил Александров", "Байдам Михайлов")
    private val adresses = arrayOf("Октябрьская 27/2", "Петровского 1", "Сергеляхское шоссе", "Лермонтова 69", "Пушкина 23")
    private val descriptions = arrayOf("Вынести мусор", "Выгулять собаку", "Убрать снег", "Принести продукты", "Уборка квартиры")

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val vDescription: TextView = view.findViewById(R.id.description)
        val vName: TextView = view.findViewById(R.id.name)
        val vAdress: TextView = view.findViewById(R.id.pointsCounter)
        val vButton: Button = view.findViewById(R.id.acceptButton)
        fun message(msg: String, view: View){
            Toast.makeText(view.context, msg, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.help_ask_adapter_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vName.text = names[position]
        holder.vAdress.text = adresses[position]
        holder.vDescription.text = descriptions[position]
        holder.vButton.setOnClickListener {
            holder.message("Вы приняли заявку от ${holder.vName.text}", holder.itemView)

        }

    }

    override fun getItemCount(): Int = names.size

    private fun getCroppedBitmap(bitmap: Bitmap): Bitmap{
        val output = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        val paint = Paint()
        val rect = Rect(0, 0, bitmap.width, bitmap.height)
        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = Color.TRANSPARENT
        canvas.drawCircle(bitmap.width.toFloat() / 2, bitmap.height.toFloat() / 2, bitmap.width.toFloat() / 2, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)
        return output
    }
}