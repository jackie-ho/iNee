package hackathon.money2020.com.inee

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

/**
 * Created by JH on 10/20/18.
 */
class BalanceAdapter : RecyclerView.Adapter<BalanceAdapter.BalanceViewHolder>() {

    val list = ArrayList<Item>()

    var incoming: Boolean = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BalanceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_balance, parent, false)
        val viewHolder = BalanceViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BalanceViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun addBalances(balances: List<Item>, isIncoming: Boolean) {
        list.clear()
        incoming = isIncoming
        list.addAll(balances)
        notifyDataSetChanged()
    }

    inner class BalanceViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val name = view.findViewById<TextView>(R.id.balanceNameText)
        val description = view.findViewById<TextView>(R.id.balanceDescription)
        val image = view.findViewById<ImageView>(R.id.profilePic)
        val amount = view.findViewById<TextView>(R.id.transactionText)
        val date = view.findViewById<TextView>(R.id.balanceDate)


        fun bind (item: Item) {
            name.text = item.name
            description.text = item.description
            amount.text = "$${item.amount}"
            date.text = item.date
            Picasso.with(itemView.context).load(item.pic).transform(CircleTransform()).into(image)

            if (incoming) amount.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorPrimaryDark))
            else amount.setTextColor(Color.RED)
        }
    }

}


