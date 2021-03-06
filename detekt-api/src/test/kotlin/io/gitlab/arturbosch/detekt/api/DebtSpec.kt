package io.gitlab.arturbosch.detekt.api

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertFails

/**
 * @author Artur Bosch
 */
class DebtSpec : Spek({

	describe("creating issues with custom debt values") {
		it("should fail on negative values") {
			assertFails { Debt(-1, -1, -1) }
		}

		it("should fail if all values are less than zero ") {
			assertFails { Debt(0, 0, 0) }
		}

		it("should print 20min, 10min and 5min") {
			assertThat(Debt.TWENTY_MINS.toString()).isEqualTo("20min")
			assertThat(Debt.TEN_MINS.toString()).isEqualTo("10min")
			assertThat(Debt.FIVE_MINS.toString()).isEqualTo("5min")
		}

		it("day, hours and min combinations should work") {
			assertThat(Debt(1, 20, 20).toString()).isEqualTo("1d 20h 20min")
			assertThat(Debt(1, 20, 0).toString()).isEqualTo("1d 20h")
			assertThat(Debt(0, 20, 0).toString()).isEqualTo("20h")
			assertThat(Debt(1, 0, 20).toString()).isEqualTo("1d 20min")
		}
	}
})
