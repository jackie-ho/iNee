package hackathon.money2020.com.inee

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_balance.*


/**
 * Created by JH on 10/20/18.
 */
class BalanceFragment : Fragment() {

    private val compositeDisposable = CompositeDisposable()
    val balanceAdapter = BalanceAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_balance, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar!!.title = "Balances and Transactions"
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        with(balanceRecycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = balanceAdapter
        }

        mChart = view.findViewById(R.id.barchart)

        compositeDisposable.add(Observable
            .merge(outgoing.clicks().map { 1 }, incoming.clicks().map { 2 }, rewards.clicks().map { 3 })
            .startWith(1)
            .subscribe {
                if (it == 1) {
                    outgoing.setBackgroundResource(R.drawable.selected_state)
                    incoming.setBackgroundResource(0)
                    rewards.setBackgroundResource(0)
                    outgoing.setTextColor(ContextCompat.getColor(context!!, R.color.white))
                    incoming.setTextColor(ContextCompat.getColor(context!!, R.color.black))
                    rewards.setTextColor(ContextCompat.getColor(context!!, R.color.black))
                    balanceQueries("outgoing")
                    balanceRecycler.visibility = View.VISIBLE
                    barchartLayout.visibility = View.GONE
                } else if (it == 2) {
                    outgoing.setBackgroundResource(0)
                    incoming.setBackgroundResource(R.drawable.selected_state)
                    rewards.setBackgroundResource(0)
                    outgoing.setTextColor(ContextCompat.getColor(context!!, R.color.black))
                    incoming.setTextColor(ContextCompat.getColor(context!!, R.color.white))
                    rewards.setTextColor(ContextCompat.getColor(context!!, R.color.black))
                    balanceQueries("incoming")
                    balanceRecycler.visibility = View.VISIBLE
                    barchartLayout.visibility = View.GONE
                } else {
                    outgoing.setBackgroundResource(0)
                    incoming.setBackgroundResource(0)
                    rewards.setBackgroundResource(R.drawable.selected_state)
                    outgoing.setTextColor(ContextCompat.getColor(context!!, R.color.black))
                    incoming.setTextColor(ContextCompat.getColor(context!!, R.color.black))
                    rewards.setTextColor(ContextCompat.getColor(context!!, R.color.white))
                    setupChart()
                    balanceRecycler.visibility = View.GONE
                    barchartLayout.visibility = View.VISIBLE
                }
            })
    }

    private fun balanceQueries(str: String) {
        INeeApplication.retrofit.balances(str)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val isIncoming = str == "incoming"
                balanceAdapter.addBalances(it.items, isIncoming)
                monthlyBillNumberText.text = "$${it.monthly_bills}"
                financialLiftNumberText.text = "$${it.financial_lift}"
                outOfPocketNumberText.text = "$${it.monthly_bills-it.financial_lift}"
                rewardNumberText.text = "$15"
            }, { it.printStackTrace() })
    }


    private fun setupChart() {
        barchartLayout.visibility = View.VISIBLE
        mChart!!.getDescription().setEnabled(false)

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart!!.setMaxVisibleValueCount(40)

        // scaling can now only be done on x- and y-axis separately
        mChart!!.setPinchZoom(false)

        mChart!!.setDrawGridBackground(false)
        mChart!!.setDrawBarShadow(false)

        mChart!!.setDrawValueAboveBar(false)
        mChart!!.setHighlightFullBarEnabled(false)

        // change the position of the y-labels
        val leftAxis = mChart!!.getAxisLeft()
        leftAxis.setValueFormatter(MyAxisValueFormatter())
        leftAxis.setAxisMinimum(0f) // this replaces setStartAtZero(true)
        mChart!!.getAxisRight().setEnabled(false)

        val xLabels = mChart!!.getXAxis()
        xLabels.setPosition(XAxis.XAxisPosition.TOP)
        xLabels.setValueFormatter(XAxisValueFormatter())

        mChart!!.getAxisLeft().setSpaceBottom(0f)

        // mChart.setDrawXLabels(false);
        // mChart.setDrawYLabels(false);
        // setting data

        val l = mChart!!.getLegend()
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM)
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT)
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL)
        l.setDrawInside(false)
        l.setFormSize(8f)
        l.setFormToTextSpace(4f)
        l.setXEntrySpace(6f)


        chart(list())
    }

    private fun list(): List<Chart> {

        val rewardList = mutableListOf<Chart>()
        with(rewardList) {
            add(Chart(500, 1700, 270, "Jan 2018"))
            add(Chart(530, 1870, 287, "Feb 2018"))
            add(Chart(560, 2057, 306, "Mar 2018"))
            add(Chart(585, 2263, 326, "Apr 2018"))
            add(Chart(615, 2489, 349, "May 2018"))
            add(Chart(640, 2738, 374, "June 2018"))
            add(Chart(680, 3012, 401, "July 2018"))
            add(Chart(725, 3313, 431, "Aug 2018"))
            add(Chart(745, 3644, 464, "Sep 2018"))
            add(Chart(784, 4009, 501, "Oct 2018"))
            add(Chart(823, 4409, 541, "Nov 2018"))
            add(Chart(877, 4850, 585, "Dec 2018"))
        }

        return rewardList
    }


    private fun chart(list: List<Chart>) {
        val yVals1 = ArrayList<BarEntry>()

        for (i in 0 until list.size) {
            val val1 = list[i].familyFunding.toFloat()
            val val2 = list[i].outOfPocket.toFloat()
            val val3 = list[i].rewards.toFloat()

            yVals1.add(
                BarEntry(
                    i.toFloat(),
                    floatArrayOf(val1, val2, val3)
                )
            )
        }

        val set1: BarDataSet

        set1 = BarDataSet(yVals1, "2018 Spend and Save Analysis")
        set1.setDrawIcons(false)
        set1.setColors(getColors())
        set1.stackLabels = arrayOf("Family Funding", "Out of Pocket", "Rewards")

        val dataSets = ArrayList<IBarDataSet>()
        dataSets.add(set1)

        val data = BarData(dataSets)
        data.setValueFormatter(MyValueFormatter())
        data.setValueTextColor(Color.WHITE)

        mChart?.setData(data)


        mChart?.setFitBars(true)
        mChart?.invalidate()
    }

    private var mChart: BarChart? = null

    private fun getColors(): List<Int> {

        val stacksize = 3

        // have as many colors as stack-values per entry
        val colors = ArrayList<Int>()
colors.add(ColorTemplate.JOYFUL_COLORS[0])
        colors.add(ColorTemplate.JOYFUL_COLORS[1])
        colors.add(ColorTemplate.JOYFUL_COLORS[3])


        return colors
    }

}



