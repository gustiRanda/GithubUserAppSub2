package com.gmind.githubuserapp

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference

class PreferenceFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var REMINDER: String

    private lateinit var isReminderOnPreference : SwitchPreference

    private lateinit var alarmReceiver: AlarmReceiver


    override fun onCreatePreferences(bundle: Bundle?, string: String?) {
        addPreferencesFromResource(R.xml.preferences)
        init()
        alarmReceiver = AlarmReceiver()
//        alarm(isReminderOnPreference)
        setSummaries()
    }

//    private fun alarm(reminderOnPreference: SwitchPreference) {
//        if (reminderOnPreference.isChecked){
//            val repeatTime = "09.32"
//            alarmReceiver.setAlarm(requireContext(), AlarmReceiver.EXTRA_TYPE, repeatTime)
//        } else{
//            alarmReceiver.delayAlarm(requireContext())
//        }
//    }

    private fun init() {
        REMINDER = resources.getString(R.string.reminder_message)

        isReminderOnPreference = findPreference<SwitchPreference>(REMINDER) as SwitchPreference
    }

    private fun setSummaries() {
        val sh = preferenceManager.sharedPreferences
        isReminderOnPreference.isCopyingEnabled = sh.getBoolean(REMINDER, false)
    }


    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }
    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        if (key == REMINDER){
            isReminderOnPreference.isCopyingEnabled = sharedPreferences.getBoolean(REMINDER, false)
            val isOn = sharedPreferences.getBoolean(REMINDER, false)
//            Toast.makeText(context, "$isOn", Toast.LENGTH_SHORT).show()
            if (isOn){
                activity?.let { alarmReceiver.setAlarm(it, AlarmReceiver.ALARM, REMINDER) }
            } else{
                context?.let { alarmReceiver.cancelAlarm(it) }
            }
        }
    }
}