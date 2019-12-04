package dev.tigrao.list.di

import dev.tigrao.commons.statemachine.StateMachine
import dev.tigrao.list.domain.ListApi
import dev.tigrao.list.domain.ListApiConverter
import dev.tigrao.list.domain.ListUseCase
import dev.tigrao.list.domain.shuffle.ArtistShuffleAlg
import dev.tigrao.list.domain.shuffle.ShuffleAlg
import dev.tigrao.list.entity.ListVO
import dev.tigrao.list.ui.LayoutManagerFactory
import dev.tigrao.list.ui.ListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val listModule = module {

    viewModel {
        ListViewModel(
            get(),
            StateMachine(Schedulers.io()),
            AndroidSchedulers.mainThread()
        )
    }

    single {
        ListUseCase(
            Schedulers.io(),
            get(),
            ListApiConverter(),
            get()
        )
    }

    single {
        get<Retrofit>().create(ListApi::class.java)
    }

    factory {
        LayoutManagerFactory()
    }

    factory<ShuffleAlg<ListVO>> {
        ArtistShuffleAlg()
    }
}
