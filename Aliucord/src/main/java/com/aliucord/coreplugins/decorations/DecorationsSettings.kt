package com.aliucord.coreplugins.decorations

import android.os.Bundle
import android.view.View
import com.aliucord.Utils
import com.aliucord.api.SettingsAPI
import com.aliucord.settings.SettingsDelegate
import com.aliucord.settings.delegate
import com.aliucord.utils.ViewUtils.addTo
import com.aliucord.widgets.BottomSheet
import com.discord.views.CheckedSetting

internal object DecorationsSettings {
    private val settings = SettingsAPI("Decorations")

    private val enableDisplayNamesDelegate = settings.delegate("enableDisplayNames", true)
    val enableDisplayNames by enableDisplayNamesDelegate

    class Sheet : BottomSheet() {
        override fun onViewCreated(view: View, bundle: Bundle?) {
            super.onViewCreated(view, bundle)
            createSetting("Show display names", enableDisplayNamesDelegate).addTo(linearLayout)
        }

        private fun createSetting(description: String, delegate: SettingsDelegate<Boolean>): CheckedSetting {
            return Utils.createCheckedSetting(
                requireContext(),
                CheckedSetting.ViewType.SWITCH,
                description,
                null
            ).apply {
                var setting by delegate
                isChecked = setting
                setOnCheckedListener { setting = !setting }
            }
        }
    }
}
