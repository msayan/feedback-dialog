package de.webtogo.library.feedback

import de.webtogo.library.feedback.core.Rate

interface FeedbackListener {

    fun feedbackCancelled()

    fun feedbackSubmitted(rate : Rate, comment : String?)
}