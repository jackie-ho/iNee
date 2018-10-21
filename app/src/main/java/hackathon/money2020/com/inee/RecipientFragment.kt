package hackathon.money2020.com.inee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

/**
 * Created by JH on 10/20/18.
 */
class RecipientFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_recipient, container, false)

        val next = view.findViewById<TextView>(R.id.nextText)
        (activity as AppCompatActivity).supportActionBar!!.title = "Transfer"

        next.setOnClickListener {
            fragmentManager?.beginTransaction()?.add(R.id.container, ContributionFragment())?.commit()
        }

        val magnusImage = view.findViewById<ImageView>(R.id.profileImage)
        Picasso.with(context)
            .load("https://images.pexels.com/photos/35537/child-children-girl-happy.jpg?auto=compress&cs=tinysrgb&h=350")
            .transform(CircleTransform())
            .into(magnusImage)

        return view
    }




}