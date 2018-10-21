package hackathon.money2020.com.inee

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.io.InputStream
import java.util.concurrent.TimeUnit


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [InvoiceFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [InvoiceFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class InvoiceFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_invoice, container, false)
        val invoiceImage = view.findViewById<ImageView>(R.id.invoiceImage)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        try {
            // get input stream
            val ims: InputStream? = context?.assets?.open("invoice.jpg")
            // load image as Drawable
            val d = Drawable.createFromStream(ims, null)
            // set image to ImageView
            invoiceImage.setImageDrawable(d)
        } catch (ex: Throwable) {
            ex.printStackTrace()
        }

        (activity as AppCompatActivity).supportActionBar!!.title = "Kids Klub Invoice"
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        return view
    }

    override fun onStart() {
        super.onStart()
        val invoiceImage = view?.findViewById<ImageView>(R.id.invoiceImage)
        val progressBar = view?.findViewById<ProgressBar>(R.id.progressBar)

        Observable.timer(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                progressBar?.visibility = View.GONE
                invoiceImage?.visibility = View.VISIBLE
            }


    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.home) {
            fragmentManager?.popBackStack()
            return true
        }
        return false
    }
}
