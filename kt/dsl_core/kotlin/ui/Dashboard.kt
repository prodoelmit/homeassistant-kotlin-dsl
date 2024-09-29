package kt.dsl_core.kotlin.ui

import dsl_core.base.HAProject
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
class Dashboard {
    val views = mutableListOf<View>()
    val mode = "yaml"
    var title: String = ""
    var icon: String? = null
    // URL-safe path to be used as filename and as dashboard path in ui
    @Transient
    var path: String = ""

    val filename: String
        get() = "dashboards/$path.yaml"

    @SerialName("show_in_sidebar")
    var showInSidebar: Boolean = true
    @SerialName("require_admin")
    var requireAdmin: Boolean = false


    fun view(init: View.() -> Unit) {
        view(View().apply(init))
    }

    fun view(existingView: View) {
        views.add(existingView)
    }
}

fun HAProject.dashboard(init: Dashboard.() -> Unit) {
    dashboard(Dashboard().apply(init))
}

