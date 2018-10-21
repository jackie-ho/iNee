package hackathon.money2020.com.inee

/**
 * Created by JH on 10/20/18.
 */

data class Contribution(val id: String)

data class Submit(val accountNumber: String, val expirateDate: String, val statusDesc: String)