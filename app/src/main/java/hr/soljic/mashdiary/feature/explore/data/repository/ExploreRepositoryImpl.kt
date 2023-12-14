package hr.soljic.mashdiary.feature.explore.data.repository

import hr.soljic.mashdiary.core.Response
import hr.soljic.mashdiary.feature.explore.data.data_source.network.ItemsService
import hr.soljic.mashdiary.feature.explore.domain.model.ExploreItemModel
import hr.soljic.mashdiary.feature.explore.domain.model.FeaturedModel
import hr.soljic.mashdiary.feature.explore.domain.model.FeaturedResponse
import hr.soljic.mashdiary.feature.home.domain.model.ItemType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ExploreRepositoryImpl @Inject constructor(
    private val service: ItemsService
) : ExploreRepository {
    override suspend fun getFeaturedItems(): Flow<List<FeaturedModel>> {
        return flow {
            delay(500)
            emit(getFeaturedResponse)
        }
    }
}


val getFeaturedResponse =
    listOf(
        FeaturedModel(
            title = "Movies",
            type = ItemType.Movie,
            data = listOf(
                ExploreItemModel(
                    title = "Titanic",
                    description = "Jack and Rose bang on a boat",
                    dateOfRelease = "1997",
                    coverPhoto = "https://m.media-amazon.com/images/M/MV5BMDdmZGU3NDQtY2E5My00ZTliLWIzOTUtMTY4ZGI1YjdiNjk3XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_.jpg",
                    id = "1"
                ),
                ExploreItemModel(
                    title = "Batman",
                    description = "Pattinson does some weird shit",
                    dateOfRelease = "2022",
                    coverPhoto = "https://image.tmdb.org/t/p/original/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg",
                    id = "2"
                ),
                ExploreItemModel(
                    title = "Dark Knight",
                    description = "Bale is a mofo",
                    dateOfRelease = "2012",
                    coverPhoto = "https://image.tmdb.org/t/p/w370_and_h556_bestv2/aMpyrCizvSdc0UIMblJ1srVgAEF.jpg",
                    id = "3"
                ),
                ExploreItemModel(
                    title = "Lorem ipsum",
                    description = "Nezz vise sta da pisem ali ovaj description ce biti malo duzi pa ajde, gubim vrijeme ovo pisuci ali mora se",
                    dateOfRelease = "2019",
                    coverPhoto = "https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/p11064851_p_v12_aj.jpg",
                    id = "4"
                ),
                ExploreItemModel(
                    title = "Loverboy",
                    description = "Dokumentarni film o nasem marku i njegovim pothvatima. erotskim ali i ostalim",
                    dateOfRelease = "2009",
                    coverPhoto = "https://i.ebayimg.com/images/g/Pc8AAOSwWBth9cf2/s-l1200.webp",
                    id = "5"
                ),
                ExploreItemModel(
                    title = "Titanic",
                    description = "Jack and Rose bang on a boat",
                    dateOfRelease = "1997",
                    coverPhoto = "https://artofthemovies.co.uk/cdn/shop/products/IMG_2672-955819.jpg?v=1686847908",
                    id = "6"
                ),
                ExploreItemModel(
                    title = "Titanic",
                    description = "Jack and Rose bang on a boat",
                    dateOfRelease = "1997",
                    coverPhoto = "https://mir-s3-cdn-cf.behance.net/project_modules/hd/a10f97108050255.5fb629683accc.jpg",
                    id = "7"
                ),
                ExploreItemModel(
                    title = "Titanic",
                    description = "Jack and Rose bang on a boat",
                    dateOfRelease = "1997",
                    coverPhoto = "https://images.photowall.com/products/51078/movie-poster-jaws.jpg?h=699&q=85",
                    id = "8"
                )
            )
        ),
        FeaturedModel(
            title = "TV Shows",
            type = ItemType.TvShow,
            data = listOf(
                ExploreItemModel(
                    title = "Titanic",
                    description = "Jack and Rose bang on a boat",
                    dateOfRelease = "1997",
                    coverPhoto = "https://images.squarespace-cdn.com/content/v1/5cf6959864dfad0001763314/1568017206611-7M82X7SXHZPE83E98VKG/Top_15_Movie_Posters_Her.jpg",
                    id = "1"
                ),
                ExploreItemModel(
                    title = "Batman",
                    description = "Pattinson does some weird shit",
                    dateOfRelease = "2022",
                    coverPhoto = "https://artofthemovies.co.uk/cdn/shop/products/IMG_1306.jpg?v=1677604566",
                    id = "2"
                ),
                ExploreItemModel(
                    title = "Dark Knight",
                    description = "Bale is a mofo",
                    dateOfRelease = "2012",
                    coverPhoto = "https://www.movieposters.com/cdn/shop/products/firstblood.af_240x360_crop_center.progressive.jpg?v=1626456817",
                    id = "3"
                ),
                ExploreItemModel(
                    title = "Lorem ipsum",
                    description = "Nezz vise sta da pisem ali ovaj description ce biti malo duzi pa ajde, gubim vrijeme ovo pisuci ali mora se",
                    dateOfRelease = "2019",
                    coverPhoto = "https://images.bauerhosting.com/legacy/empire-images/features/59e8d795405a5c6805947751/47%20Lord%20of%20War.jpg?auto=format&w=1440&q=80",
                    id = "4"
                ),
                ExploreItemModel(
                    title = "Loverboy",
                    description = "Dokumentarni film o nasem marku i njegovim pothvatima. erotskim ali i ostalim",
                    dateOfRelease = "2009",
                    coverPhoto = "https://i.guim.co.uk/img/media/7f9f5866aa535d95cd69e8f931f6f21b25c5227e/0_0_2025_3000/master/2025.jpg?width=300&quality=45&auto=format&fit=max&dpr=2&s=5a3cc00304068a9a45d7cf014e1db54d",
                    id = "5"
                ),
                ExploreItemModel(
                    title = "Titanic",
                    description = "Jack and Rose bang on a boat",
                    dateOfRelease = "1997",
                    coverPhoto = "https://static.displate.com/280x392/displate/2023-04-19/a10b29a74c11f350368682f21b346321_0bf3703beff1d1bd9df25ab4a41755c8.jpg",
                    id = "6"
                ),
                ExploreItemModel(
                    title = "Titanic",
                    description = "Jack and Rose bang on a boat",
                    dateOfRelease = "1997",
                    coverPhoto = "https://www.bluedogposters.com.au/cdn/shop/products/PP34925_1400x.jpg?v=1674016516",
                    id = "7"
                ),
                ExploreItemModel(
                    title = "Titanic",
                    description = "Jack and Rose bang on a boat",
                    dateOfRelease = "1997",
                    coverPhoto = "https://static01.nyt.com/images/2017/09/15/arts/24movie-posters8/24movie-posters8-superJumbo.jpg",
                    id = "8"
                )
            )
        )
    )





