import java.util.Calendar

/**
 * this is used for automatic theme change on the basis of time
 * Returns [Boolean] based on current time.
 * Returns true if hours are between 06:00 pm - 07:00 am
 */

fun isNight(): Boolean {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return (currentHour <= 7 || currentHour >= 18)
}
