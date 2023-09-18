package com.example.secondgallery.presentation.profile

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.login.User
import com.example.domain.entity.photo.PhotoModel
import com.example.secondgallery.App
import com.example.secondgallery.R
import com.example.secondgallery.adapter.PaginationScrollListener
import com.example.secondgallery.adapter.RecyclerAdapter
import com.example.secondgallery.adapter.RecyclerAdapterProfile
import com.example.secondgallery.databinding.FragmentProfileBinding
import com.example.secondgallery.presentation.basemvp.basePhoto.BasePhotoFragment
import com.example.secondgallery.utils.DateUtils
import com.example.secondgallery.utils.PhotoType
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class ProfileFragment :
    BasePhotoFragment<ProfileView, ProfilePresenter, FragmentProfileBinding>(PhotoType.New.raw, 2),
    ProfileView {

    lateinit var user: String
    private var id: Int? = null
    override var spanCount: Int = 4
    override var spanCountLand: Int = 8
    private lateinit var adapter: RecyclerAdapterProfile
    private lateinit var linearLayoutManager: GridLayoutManager

    @InjectPresenter
    override lateinit var presenter: ProfilePresenter

    @ProvidePresenter
    fun providePresenter(): ProfilePresenter = App.appComponent.provideProfilePresenter()

    override fun initializeBinding(): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // requireActivity().navigationView.visibility = View.VISIBLE

        presenter.getCurrentUser()
        user = id.toString()
        setUpListeners()
    }

    override fun initRecyclerView(photos: ArrayList<PhotoModel>) {
        adapter = RecyclerAdapterProfile(photos, object : RecyclerAdapter.Callback {
            override fun onImageClicked(item: PhotoModel) {
                presenter.onImageClicked(item)
            }
        })
        linearLayoutManager = GridLayoutManager(this.context, spanCount)
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            linearLayoutManager = GridLayoutManager(this.context, spanCountLand)
        }
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        recyclerView.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
            override fun loadMoreItems() {
                presenter.checkSearch()
            }
        })
    }

    override fun setUpUI(user: User) {
        binding.tvCountOfLoaded.text = presenter.photoSize().toString()
        id = user.id
        binding.tvUsername.text = user.username
        binding.tvBirthday.text = DateUtils.checkDateFormat(user.birthday.toString())
    }

    override fun getSearchPhotos(name: String) {
    }

    override fun setUpListeners() {

        binding.toolbar.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }

        binding.iconSettings.setOnClickListener {
            findNavController().navigate(R.id.settingsFragment)
        }

    }

    override fun addExtraItems(position: Int, quantity: Int) {
        adapter.notifyItemRangeInserted(position, quantity)
    }

    override fun addNewItems() {
        adapter.notifyDataSetChanged()
    }

    override fun addNewSearchItems() {
        adapter.notifyDataSetChanged()
    }

    override fun initViews() {

        recyclerView = requireView().findViewById(R.id.recyclerViewProfile)
        swipeRefreshLayout = requireView().findViewById(R.id.swiperefresh)
        placeholder = requireView().findViewById(R.id.profilePlaceholder)

        swipeRefreshLayout.setOnRefreshListener {
            presenter.onSwipeRefresh()
        }

        swipeRefreshLayout.setColorSchemeColors(
            ResourcesCompat.getColor(resources, R.color.black, null),
            ResourcesCompat.getColor(resources, R.color.pink, null)
        )

        progressBar = requireView().findViewById(R.id.progressbar)
    }

}