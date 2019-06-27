package ti6b.pk.skire

import android.content.Context
import ti6b.pk.skire.model.Tips

class TipsPresenter (val context : Context, val view : TipsView) {

    fun onFetchingData(){

        val res = context.resources

        val image = res.getStringArray(R.array.tips_img)
        val title = res.getStringArray(R.array.tips_title)
        val source = res.getStringArray(R.array.tips_source)
        val url = res.getStringArray(R.array.tips_url)

        val datas: MutableList<Tips> = mutableListOf()

        title.indices.forEach { i ->
            datas.add(Tips(image[i], title[i], source[i], url[i]))
        }

        view.getData(datas)

    }
}