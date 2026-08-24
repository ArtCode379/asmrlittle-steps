package asmrtraders.babycare.asmrlittlesteps.data.model

import androidx.annotation.StringRes
import asmrtraders.babycare.asmrlittlesteps.R

enum class ProductCategory(@field:StringRes val titleRes: Int) {
    FEEDING(R.string.gczct_category_feeding),
    NAPPIES(R.string.gczct_category_nappies),
    CLOTHING(R.string.gczct_category_clothing),
    TRAVEL(R.string.gczct_category_travel),
    CARE(R.string.gczct_category_care),
    MATERNITY(R.string.gczct_category_maternity),
}
