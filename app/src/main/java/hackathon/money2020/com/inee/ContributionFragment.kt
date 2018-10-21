package hackathon.money2020.com.inee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.jakewharton.rxbinding3.widget.textChangeEvents
import com.mikepenz.materialize.MaterializeBuilder
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function4
import kotlinx.android.synthetic.main.fragment_contribution.*


/**
 * Created by JH on 10/20/18.
 */
class ContributionFragment : Fragment() {

    lateinit var kidsKlubFullText: TextView
    lateinit var kidsKlubFullSwitch: Switch
    lateinit var kidsKlubAmountText: TextView
    lateinit var kidsKlubAmountEditText: EditText
    lateinit var kidsKlubMonthlyText: TextView
    lateinit var kidsKlubMonthlySwitch: Switch
    lateinit var kidsKlubLayout: ConstraintLayout
    lateinit var kidsKlubDollarText: TextView
    lateinit var kidsKlubNameText: TextView
    lateinit var kidsKlubViewText: TextView
    var kidsKlubDisplay: Boolean = true

    lateinit var wushuFullText: TextView
    lateinit var wushuFullSwitch: Switch
    lateinit var wushuAmountText: TextView
    lateinit var wushuAmountEditText: EditText
    lateinit var wushuMonthlyText: TextView
    lateinit var wushuMonthlySwitch: Switch
    lateinit var wushuLayout: ConstraintLayout
    lateinit var wushuDollarText: TextView
    var wushuDisplay: Boolean = true

    lateinit var chineseClassFullText: TextView
    lateinit var chineseClassFullSwitch: Switch
    lateinit var chineseClassAmountText: TextView
    lateinit var chineseClassAmountEditText: EditText
    lateinit var chineseClassMonthlyText: TextView
    lateinit var chineseClassMonthlySwitch: Switch
    lateinit var chineseClassLayout: ConstraintLayout
    lateinit var chineseClassDollarText: TextView
    var chineseClassDisplay: Boolean = true

    lateinit var legolandFullText: TextView
    lateinit var legolandFullSwitch: Switch
    lateinit var legolandAmountText: TextView
    lateinit var legolandAmountEditText: EditText
    lateinit var legolandLayout: ConstraintLayout
    lateinit var legolandDollarText: TextView
    var legolandDisplay: Boolean = true

    lateinit var totalText: TextView

    private val compositeDisposable = CompositeDisposable()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_contribution, container, false)
        MaterializeBuilder().withActivity(activity).build()

        kidsKlubFullText = view.findViewById(R.id.kidsklubFullText)
        kidsKlubFullSwitch = view.findViewById(R.id.kidsKlubFullSwitch)
        kidsKlubAmountText = view.findViewById(R.id.kidsklubAmountText)
        kidsKlubAmountEditText = view.findViewById(R.id.kidsklubAmountEditText)
        kidsKlubMonthlyText = view.findViewById(R.id.kidsklubMonthlyText)
        kidsKlubMonthlySwitch = view.findViewById(R.id.kidsKlubMonthlySwitch)
        kidsKlubLayout = view.findViewById(R.id.kidsKlubLayout)
        kidsKlubDollarText = view.findViewById(R.id.kidsklubDollarText)
        kidsKlubNameText = view.findViewById(R.id.kidsklubText)
        kidsKlubViewText = view.findViewById(R.id.kidsKlubViewText)
        kidsKlubNameText.setOnClickListener { displayKidsKlub(kidsKlubDisplay) }
        kidsKlubViewText.setOnClickListener { fragmentManager?.beginTransaction()
            ?.addToBackStack("invoice")?.add(R.id.container, InvoiceFragment(), "invoice")?.commit()}

        wushuFullText = view.findViewById(R.id.wushuFullText)
        wushuFullSwitch = view.findViewById(R.id.wushuFullSwitch)
        wushuAmountText = view.findViewById(R.id.wushuAmountText)
        wushuAmountEditText = view.findViewById(R.id.wushuAmountEditText)
        wushuMonthlyText = view.findViewById(R.id.wushuMonthlyText)
        wushuMonthlySwitch = view.findViewById(R.id.wushuMonthlySwitch)
        wushuLayout = view.findViewById(R.id.wushuLayout)
        wushuDollarText = view.findViewById(R.id.wushuDollarText)
        wushuLayout.setOnClickListener { displaywushu(wushuDisplay) }

        chineseClassFullText = view.findViewById(R.id.chineseClassFullText)
        chineseClassFullSwitch = view.findViewById(R.id.chineseClassFullSwitch)
        chineseClassAmountText = view.findViewById(R.id.chineseClassAmountText)
        chineseClassAmountEditText = view.findViewById(R.id.chineseClassAmountEditText)
        chineseClassMonthlyText = view.findViewById(R.id.chineseClassMonthlyText)
        chineseClassMonthlySwitch = view.findViewById(R.id.chineseClassMonthlySwitch)
        chineseClassLayout = view.findViewById(R.id.chineseClassLayout)
        chineseClassDollarText = view.findViewById(R.id.chineseClassDollarText)
        chineseClassLayout.setOnClickListener { displayChineseClass(chineseClassDisplay) }


        legolandFullText = view.findViewById(R.id.legolandFullText)
        legolandFullSwitch = view.findViewById(R.id.legolandFullSwitch)
        legolandAmountText = view.findViewById(R.id.legolandAmountText)
        legolandAmountEditText = view.findViewById(R.id.legolandAmountEditText)
        legolandLayout = view.findViewById(R.id.legolandLayout)
        legolandDollarText = view.findViewById(R.id.legolandsDollarText)
        legolandLayout.setOnClickListener { displaylegoland(legolandDisplay) }

        totalText = view.findViewById(R.id.totalNumberText)

        view.findViewById<TextView>(R.id.nextText).setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.addToBackStack("confirm")?.add(R.id.container, ConfirmFragment(), "confirm")?.commit()
        }

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        compositeDisposable.add(Observable.combineLatest<Double, Double, Double, Double, Double>(kidsKlubAmountEditText.textChangeEvents()
            .map { it.text.toString().toDouble() },
            wushuAmountEditText.textChangeEvents().map { it.text.toString().toDouble() },
            chineseClassAmountEditText.textChangeEvents().map { it.text.toString().toDouble() },
            legolandAmountEditText.textChangeEvents().map { it.text.toString().toDouble() },
            Function4 { a: Double, b: Double, c: Double, d: Double -> return@Function4 a + b + c + d })
            .subscribe {
                val money = "%.2f".format(it)
                totalText.text = "$$money"
            })

        kidsKlubAmountEditText.setText("0")
        wushuAmountEditText.setText("0")
        chineseClassAmountEditText.setText("0")
        legolandAmountEditText.setText("0")


        legolandFullSwitch.setOnClickListener {
            legolandAmountEditText.setText("1700")
        }
        kidsKlubFullSwitch.setOnClickListener {
            kidsKlubAmountEditText.setText("2250")
        }
        wushuFullSwitch.setOnClickListener {
            wushuAmountEditText.setText("425")
        }
        chineseClassFullSwitch.setOnClickListener {
            chineseClassAmountEditText.setText("170")
        }

    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar!!.title = "Contribute"
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun displayKidsKlub(value: Boolean) {
        kidsKlubFullText.visibility = if (value) View.VISIBLE else View.GONE
        kidsKlubFullSwitch.visibility = if (value) View.VISIBLE else View.GONE
        kidsKlubAmountText.visibility = if (value) View.VISIBLE else View.GONE
        kidsKlubAmountEditText.visibility = if (value) View.VISIBLE else View.GONE
        kidsKlubMonthlyText.visibility = if (value) View.VISIBLE else View.GONE
        kidsKlubMonthlySwitch.visibility = if (value) View.VISIBLE else View.GONE
        kidsklubDollarText.visibility = if (value) View.VISIBLE else View.GONE
        kidsKlubDisplay = !value
    }

    fun displaywushu(value: Boolean) {
        wushuFullText.visibility = if (value) View.VISIBLE else View.GONE
        wushuFullSwitch.visibility = if (value) View.VISIBLE else View.GONE
        wushuAmountText.visibility = if (value) View.VISIBLE else View.GONE
        wushuAmountEditText.visibility = if (value) View.VISIBLE else View.GONE
        wushuMonthlyText.visibility = if (value) View.VISIBLE else View.GONE
        wushuMonthlySwitch.visibility = if (value) View.VISIBLE else View.GONE
        wushuDollarText.visibility = if (value) View.VISIBLE else View.GONE
        wushuDisplay = !value
    }

    fun displayChineseClass(value: Boolean) {
        chineseClassFullText.visibility = if (value) View.VISIBLE else View.GONE
        chineseClassFullSwitch.visibility = if (value) View.VISIBLE else View.GONE
        chineseClassAmountText.visibility = if (value) View.VISIBLE else View.GONE
        chineseClassAmountEditText.visibility = if (value) View.VISIBLE else View.GONE
        chineseClassMonthlyText.visibility = if (value) View.VISIBLE else View.GONE
        chineseClassMonthlySwitch.visibility = if (value) View.VISIBLE else View.GONE
        chineseClassDollarText.visibility = if (value) View.VISIBLE else View.GONE

        chineseClassDisplay = !value
    }

    fun displaylegoland(value: Boolean) {
        legolandFullText.visibility = if (value) View.VISIBLE else View.GONE
        legolandFullSwitch.visibility = if (value) View.VISIBLE else View.GONE
        legolandAmountText.visibility = if (value) View.VISIBLE else View.GONE
        legolandAmountEditText.visibility = if (value) View.VISIBLE else View.GONE
        legolandDollarText.visibility = if (value) View.VISIBLE else View.GONE
        legolandDisplay = !value
    }
}