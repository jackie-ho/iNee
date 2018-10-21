package hackathon.money2020.com.inee

/**
 * Created by JH on 10/20/18.
 */

data class Contribution(val id: String)

data class Submit(val accountNumber: String, val expirateDate: String, val statusDesc: String)

data class Balance(val monthly_bills: Int, val financial_lift: Int, val items: List<Item>)

data class Item(val name: String, val description: String, val amount: Int, val pic: String, val date: String)

data class Chart(val rewards: Int, val outOfPocket: Int, val familyFunding: Int, val date: String)