package joandersongoncalves.example.tasktimer

import android.provider.BaseColumns

object TasksContract {
    internal const val TABLE_NAME = "Tasks"

    //tasks filds
    object Columns {
        const val ID = BaseColumns._ID
        const val  TASK_NAME = "Name"
        const val TASK_DESCIPTION = "Description"
        const val TASK_SORT_ORDER = "SortOrder"
    }
}