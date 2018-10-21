package hackathon.money2020.com.inee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import hackathon.money2020.com.inee.INeeApplication.Companion.retrofit
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by JH on 10/20/18.
 */
class ConfirmFragment : Fragment() {

    lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_confirm, container, false)

        (activity as AppCompatActivity).supportActionBar!!.title = "Confirm"
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        progressBar = view.findViewById(R.id.confirmProgressBar)

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val confirmButton = view.findViewById<TextView>(R.id.submitText)

        confirmButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            retrofit.submit()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    progressBar.visibility = View.GONE
                    ConfirmDialogFragment().show(fragmentManager,"")
                    Observable.timer(1000, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            fragmentManager?.beginTransaction()?.add(R.id.container, BalanceFragment(),"balance")
                                ?.commit()
                        }
                }, { it.printStackTrace()
                    progressBar.visibility = View.GONE})
        }
    }


}