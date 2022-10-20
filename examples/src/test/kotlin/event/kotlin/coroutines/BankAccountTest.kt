
package event.kotlin.coroutines

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class BankAccountTest {

    @Test
    fun `when depositing the bank balance should increase`() = runBlocking {

        val testBankAccount = BankAccount()
        val expectedBalance = 110

        testBankAccount.deposit(10)

        assertThat(testBankAccount.balance).isEqualTo(expectedBalance)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when depositing the bank balance should increase (with no delays)`() = runTest { //runTest same as runBlocking but skips delays

        val testBankAccount = BankAccount()
        val expectedBalance = 110

        testBankAccount.deposit(10)

        assertThat(testBankAccount.balance).isEqualTo(expectedBalance)
    }

}

class BankAccount {

    var balance: Int = 100

    suspend fun deposit(amount: Int) {
        delay(1000)
        balance += amount
    }
}


//To test a suspend function you need to create coroutine first