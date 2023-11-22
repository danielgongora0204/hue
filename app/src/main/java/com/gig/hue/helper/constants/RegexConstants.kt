package com.gig.hue.com.gig.hue.helper.constants

object RegexConstants {
    public const val EMAIL_ADDRESS_REGEX = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
    public const val PASSWORD_ADDRESS_REGEX = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%^&*]).*$"
}