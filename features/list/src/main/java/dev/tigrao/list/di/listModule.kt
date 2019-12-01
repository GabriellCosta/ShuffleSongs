package dev.tigrao.list.di

import dev.tigrao.commons.statemachine.StateMachine
import dev.tigrao.list.LayoutManagerFactory
import dev.tigrao.list.ListApi
import dev.tigrao.list.ListApiConverter
import dev.tigrao.list.ListUseCase
import dev.tigrao.list.ListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val listModule = module {

    viewModel {
        ListViewModel(get(), StateMachine(Schedulers.io()), AndroidSchedulers.mainThread())
    }

    single {
        ListUseCase(Schedulers.io(), get(), ListApiConverter())
    }

    single {
        get<Retrofit>().create(ListApi::class.java)
    }

    factory {
        LayoutManagerFactory()
    }
}
