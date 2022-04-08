package com.example.mpit.view.mainActivity.fragmentVolunteer

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.createBitmap
import androidx.recyclerview.widget.RecyclerView
import com.example.mpit.R

class VolunteerTopAdapter : RecyclerView.Adapter<VolunteerTopAdapter.ViewHolder>() {

    private val names = arrayOf("Данил Акимов", "Андрей Борисов", "Витя Корякин", "Кирилл Кычкин")
    private val points = arrayOf(233, 201, 150, 21)
    private val images = arrayOf(
        Color.BLUE,
        Color.GREEN,
        Color.RED,
        Color.YELLOW
    )

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val vImage: ImageView = view.findViewById(R.id.imageView)
        val vName: TextView = view.findViewById(R.id.name)
        val vPoints: TextView = view.findViewById(R.id.pointsCounter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.volunteer_top_adapter_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vName.text = names[position]
        holder.vPoints.text = "Баллы: ${points[position]}"
        val bitmap = createBitmap(50,50, Bitmap.Config.ARGB_8888)
        bitmap.eraseColor(images[position])
        val circle = getCroppedBitmap(bitmap)
        holder.vImage.setImageBitmap(circle)
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