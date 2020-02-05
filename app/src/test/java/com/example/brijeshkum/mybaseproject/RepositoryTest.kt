package com.homeservices.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.brijeshkum.mybaseproject.db.Repository
import com.example.brijeshkum.mybaseproject.db.RepositoryInterface
import org.junit.*

import org.junit.rules.TestRule
//import org.mockito.Mockito

/**
 * Created by 51778499 on 14,May,2019
 * Hcl Technologies,
 * India.
 */
class RepositoryTest {

    lateinit var repositoryInterface: RepositoryInterface
    lateinit var repository: Repository
    @get:Rule
    var rule: TestRule =
        InstantTaskExecutorRule()//to remove error related to testing loading live data var in @RepositoryDemo

//    @Before
//    fun setUp() {
//        repositoryInterface = Mockito.mock(repositoryInterface::class.java)
//        repository = Repository(repositoryInterface)
//    }
//
//    @After
//    fun tearDown() {
//    }
//
//    //Ayaz
//    @Test
//    fun getCurrentSignedInVendor() {
//        val user: FirebaseUser = Mockito.mock(FirebaseUser::class.java)
//        Mockito.`when`(repositoryInterface.getCurrentUser()).thenReturn(user)
//        Mockito.`when`(user.phoneNumber).thenReturn("8745811286")
//
//        val liveData = repository.getCurrentSignedInVendor()
//        Assert.assertNull(liveData.value)
//
//    }
//
//    //Ayaz
//    @Test
//    fun getcurrentSignedInUser() {
//        val user: FirebaseUser = Mockito.mock(FirebaseUser::class.java)
//        Mockito.`when`(repositoryInterface.getCurrentUser()).thenReturn(user)
//        Mockito.`when`(user.phoneNumber).thenReturn("8745811286")
//
//        val liveData = repository.getCurrentSignedInUser()
//        Assert.assertNull(liveData.value)
//    }
//
//    @Test
//    fun getServices() {
//        val task: Task<*> = Mockito.mock(Task::class.java)
//        val querySnapshot: QuerySnapshot = Mockito.mock(QuerySnapshot::class.java)
//        Mockito.`when`(task.isSuccessful).thenReturn(true)
//        Mockito.`when`(task.result).thenReturn(querySnapshot)
//        val documentSnapshot: DocumentSnapshot = Mockito.mock(DocumentSnapshot::class.java)
//        val service = Service(1, "Demo", "")
//        Mockito.`when`(documentSnapshot.toObject(Service::class.java)).thenReturn(service)
//        val listDoc: List<DocumentSnapshot> = arrayListOf(documentSnapshot)
//        Mockito.`when`(querySnapshot.documents).thenReturn(listDoc)
//        Mockito.`when`(repositoryInterface.getServices(repository, Fun.GET_SERVICE))
//            .then { repository.onComplete(task as Task<QuerySnapshot>, Fun.GET_SERVICE) }
//        val liveData = repository.getServices()
//        Assert.assertEquals(liveData.value!!.size, 1)
//    }
//
//    @Test
//    fun getAllVendor() {
//        val task: Task<*> = Mockito.mock(Task::class.java)
//        val querySnapshot: QuerySnapshot = Mockito.mock(QuerySnapshot::class.java)
//        Mockito.`when`(task.isSuccessful).thenReturn(true)
//        Mockito.`when`(task.result).thenReturn(querySnapshot)
//        val documentSnapshot: DocumentSnapshot = Mockito.mock(DocumentSnapshot::class.java)
//        val vendor = Mockito.mock(Vendor::class.java)
//        Mockito.`when`(documentSnapshot.toObject(Vendor::class.java)).thenReturn(vendor)
//        Mockito.`when`(documentSnapshot.reference).thenReturn(Mockito.mock(DocumentReference::class.java))
//        Mockito.`when`(documentSnapshot.reference.id).thenReturn("")
//        val listDoc: List<DocumentSnapshot> = arrayListOf(documentSnapshot)
//        Mockito.`when`(querySnapshot.documents).thenReturn(listDoc)
//        Mockito.`when`(repositoryInterface.getAllVendor(repository, Fun.GET_ALL_VENDOR))
//            .then { repository.onComplete(task as Task<QuerySnapshot>, Fun.GET_ALL_VENDOR) }
//        val liveData = repository.getAllVendor()
//        Assert.assertEquals(liveData.value!!.size, 1)
//    }
//
//    @Test
//    fun getVendor() {
//        val task: Task<*> = Mockito.mock(Task::class.java)
//        val querySnapshot: QuerySnapshot = Mockito.mock(QuerySnapshot::class.java)
//        Mockito.`when`(task.isSuccessful).thenReturn(true)
//        Mockito.`when`(task.result).thenReturn(querySnapshot)
//        val documentSnapshot: DocumentSnapshot = Mockito.mock(DocumentSnapshot::class.java)
//        val vendor = Mockito.mock(Vendor::class.java)
//        Mockito.`when`(vendor.phone).thenReturn("9988776655")
//        Mockito.`when`(documentSnapshot.toObject(Vendor::class.java)).thenReturn(vendor)
//        Mockito.`when`(documentSnapshot.reference).thenReturn(Mockito.mock(DocumentReference::class.java))
//        Mockito.`when`(documentSnapshot.reference.id).thenReturn("")
//        val listDoc: List<DocumentSnapshot> = arrayListOf(documentSnapshot)
//        Mockito.`when`(querySnapshot.documents).thenReturn(listDoc)
//        Mockito.`when`(repositoryInterface.getVendor("9988776655", repository, Fun.GET_VENDOR))
//            .then { repository.onComplete(task as Task<QuerySnapshot>, Fun.GET_VENDOR) }
//        val liveData = repository.getVendor("9988776655")
//        Assert.assertEquals(liveData.value!!.phone, "9988776655")
//    }
//
//    @Test
//    fun getUser() {
//        val task: Task<*> = Mockito.mock(Task::class.java)
//        val querySnapshot: QuerySnapshot = Mockito.mock(QuerySnapshot::class.java)
//        Mockito.`when`(task.isSuccessful).thenReturn(true)
//        Mockito.`when`(task.result).thenReturn(querySnapshot)
//        val documentSnapshot: DocumentSnapshot = Mockito.mock(DocumentSnapshot::class.java)
//        val user = Mockito.mock(User::class.java)
//        Mockito.`when`(user.phone).thenReturn("9988776655")
//        Mockito.`when`(documentSnapshot.toObject(User::class.java)).thenReturn(user)
//        Mockito.`when`(documentSnapshot.reference).thenReturn(Mockito.mock(DocumentReference::class.java))
//        Mockito.`when`(documentSnapshot.reference.id).thenReturn("")
//        val listDoc: List<DocumentSnapshot> = arrayListOf(documentSnapshot)
//        Mockito.`when`(querySnapshot.documents).thenReturn(listDoc)
//        Mockito.`when`(repositoryInterface.getUser("9988776655", repository, Fun.GET_USER))
//            .then { repository.onComplete(task as Task<QuerySnapshot>, Fun.GET_USER) }
//        val liveData = repository.getUser("9988776655")
//        Assert.assertEquals(liveData.value!!.phone, "9988776655")
//    }
//
//    @Test
//    fun postVendor() {
//        val void: Void? = null
//        val vendor = Vendor()
//        Mockito.`when`(repositoryInterface.postVendor(vendor, repository, repository))
//            .then { repository.onSuccess(void) }
//        val liveData = repository.postVendor(vendor)
//        Assert.assertEquals(liveData.value!!.status, true)
//    }
//
//
//    @Test
//    fun postUser() {
//        val void: Void? = null
//        val user = User()
//        Mockito.`when`(repositoryInterface.postUser(user, repository, repository))
//            .then { repository.onSuccess(void) }
//        val liveData = repository.postUser(user)
//        Assert.assertEquals(liveData.value!!.status, true)
//    }
//
//    @Test
//    fun updateUser() {
//        val void: Void? = null
//        val user = User()
//        Mockito.`when`(repositoryInterface.updateUser(user, repository, repository))
//            .then { repository.onSuccess(void) }
//        val liveData = repository.updateUser(user)
//        Assert.assertEquals(liveData.value!!.status, true)
//    }
//
//    @Test
//    fun updateVendor() {
//        val void: Void? = null
//        val vendor = Vendor()
//        Mockito.`when`(repositoryInterface.updateVendor(vendor, repository, repository))
//            .then { repository.onSuccess(void) }
//        val liveData = repository.updateVendor(vendor)
//        Assert.assertEquals(liveData.value!!.status, true)
//    }
//
//    //Ayaz
//    @Test
//    fun getVendors() {
//        val task: Task<*> = Mockito.mock(Task::class.java)
//        val querySnapshot: QuerySnapshot = Mockito.mock(QuerySnapshot::class.java)
//        Mockito.`when`(task.isSuccessful).thenReturn(true)
//        Mockito.`when`(task.result).thenReturn(querySnapshot)
//        val documentSnapshot: DocumentSnapshot = Mockito.mock(DocumentSnapshot::class.java)
//        val vendor = Vendor()
//        Mockito.`when`(documentSnapshot.toObject(Vendor::class.java)).thenReturn(vendor)
//        Mockito.`when`(documentSnapshot.reference).thenReturn(Mockito.mock(DocumentReference::class.java))
//        Mockito.`when`(documentSnapshot.reference.id).thenReturn("")
//        val listDoc: List<DocumentSnapshot> = arrayListOf(documentSnapshot)
//        Mockito.`when`(querySnapshot.documents).thenReturn(listDoc)
//        val service = Service(1, "Demo", "")
//        Mockito.`when`(documentSnapshot.reference).thenReturn(Mockito.mock(DocumentReference::class.java))
//        Mockito.`when`(documentSnapshot.reference.id).thenReturn("")
//        Mockito.`when`(repositoryInterface.getVendors(service, repository, Fun.GET_VENDORS))
//            .then { repository.onComplete(task as Task<QuerySnapshot>, Fun.GET_VENDORS) }
//        val liveData = repository.getVendors(service)
//        Assert.assertEquals(liveData.value!!.size, 1)
//    }
//
//    @Test
//    fun uploadImage() {
//    }
//
//    @Test
//    fun postVendorRating() {
//        val void: Void? = null
//        //val rating = Rating()
//        val vendor = Vendor()
//        val user = User()
//        user.phone = ""
//        user.name = ""
//        user.image = ""
//        vendor.phone=""
//        val rating  = 5.0
//        val review = ""
//        Mockito.`when`(repositoryInterface.postVendorRating(vendor, user, rating, review, repository, repository))
//            .then { repository.onSuccess(void) }
//        val liveData = repository.postVendorRating(vendor, user, rating, review)
//        Assert.assertEquals(liveData.value!!.status, true)
//    }
//
//    @Test
//    fun updateVendorRating() {
//        val void: Void? = null
//        val rating = Rating()
//        Mockito.`when`(repositoryInterface.updateVendorRating(rating, repository, repository))
//            .then { repository.onSuccess(void) }
//        val liveData = repository.updateVendorRating(rating)
//        Assert.assertEquals(liveData.value!!.status, true)
//    }
//
//    @Test
//    fun deleteVendorRating() {
//        val void: Void? = null
//        val rating = Rating()
//        Mockito.`when`(repositoryInterface.deleteVendorRating(rating, repository, repository))
//            .then { repository.onSuccess(void) }
//        val liveData = repository.deleteVendorRating(rating)
//        Assert.assertEquals(liveData.value!!.status, true)
//    }
//
//    @Test
//    fun getAllRatingVendor() {
//        val task: Task<*> = Mockito.mock(Task::class.java)
//        val querySnapshot: QuerySnapshot = Mockito.mock(QuerySnapshot::class.java)
//        Mockito.`when`(task.isSuccessful).thenReturn(true)
//        Mockito.`when`(task.result).thenReturn(querySnapshot)
//        val documentSnapshot: DocumentSnapshot = Mockito.mock(DocumentSnapshot::class.java)
//        val vendor = Vendor()
//        val rating = Rating()
//        Mockito.`when`(documentSnapshot.toObject(Rating::class.java)).thenReturn(rating)
//        Mockito.`when`(documentSnapshot.reference).thenReturn(Mockito.mock(DocumentReference::class.java))
//        Mockito.`when`(documentSnapshot.reference.id).thenReturn("")
//        val listDoc: List<DocumentSnapshot> = arrayListOf(documentSnapshot)
//        Mockito.`when`(querySnapshot.documents).thenReturn(listDoc)
//        Mockito.`when`(repositoryInterface.getAllRatingVendor(vendor, repository, Fun.GET_ALL_RATING_VENDOR))
//            .then { repository.onComplete(task as Task<QuerySnapshot>, Fun.GET_ALL_RATING_VENDOR) }
//        val liveData = repository.getAllRatingVendor(vendor, true)
//        Assert.assertEquals(liveData.value!!.size, 1)
//    }
//
//    @Test
//    fun getAllRatingUser() {
//        val task: Task<*> = Mockito.mock(Task::class.java)
//        val querySnapshot: QuerySnapshot = Mockito.mock(QuerySnapshot::class.java)
//        Mockito.`when`(task.isSuccessful).thenReturn(true)
//        Mockito.`when`(task.result).thenReturn(querySnapshot)
//        val documentSnapshot: DocumentSnapshot = Mockito.mock(DocumentSnapshot::class.java)
//        val user = User()
//        val rating = Rating()
//        Mockito.`when`(documentSnapshot.toObject(Rating::class.java)).thenReturn(rating)
//        Mockito.`when`(documentSnapshot.reference).thenReturn(Mockito.mock(DocumentReference::class.java))
//        Mockito.`when`(documentSnapshot.reference.id).thenReturn("")
//        val listDoc: List<DocumentSnapshot> = arrayListOf(documentSnapshot)
//        Mockito.`when`(querySnapshot.documents).thenReturn(listDoc)
//        Mockito.`when`(repositoryInterface.getAllRatingUser(user, repository, Fun.GET_ALL_RATING_USER))
//            .then { repository.onComplete(task as Task<QuerySnapshot>, Fun.GET_ALL_RATING_USER) }
//        val liveData = repository.getAllRatingUser(user)
//        Assert.assertEquals(liveData.value!!.size, 1)
//    }
//
//    @Test
//    fun postFavorite() {
//        val user = User()
//        val vendor = Vendor()
//        val liveData = repository.postFavorite(vendor, user)
//        Assert.assertNotNull(liveData)
//    }
//
//    @Test
//    fun getAllFavorite() {
//        val task: Task<*> = Mockito.mock(Task::class.java)
//        val querySnapshot: QuerySnapshot = Mockito.mock(QuerySnapshot::class.java)
//        Mockito.`when`(task.isSuccessful).thenReturn(true)
//        Mockito.`when`(task.result).thenReturn(querySnapshot)
//        val documentSnapshot: DocumentSnapshot = Mockito.mock(DocumentSnapshot::class.java)
//        val user = User()
//        val vendor = Vendor()
//        Mockito.`when`(documentSnapshot.toObject(Vendor::class.java)).thenReturn(vendor)
//        Mockito.`when`(documentSnapshot.reference).thenReturn(Mockito.mock(DocumentReference::class.java))
//        Mockito.`when`(documentSnapshot.reference.id).thenReturn("")
//        val listDoc: List<DocumentSnapshot> = arrayListOf(documentSnapshot)
//        Mockito.`when`(querySnapshot.documents).thenReturn(listDoc)
//        Mockito.`when`(repositoryInterface.getAllFavorite(user, repository, Fun.GET_ALL_FAV))
//            .then { repository.onComplete(task as Task<QuerySnapshot>, Fun.GET_ALL_FAV) }
//        val liveData = repository.getAllFavorite(user)
//        Assert.assertEquals(liveData.value!!.size, 1)
//    }
//
//    //Ayaz
//    @Test
//    fun deleteVendorFavorite() {
//        val void: Void? = null
//        var user: User = Mockito.mock(User::class.java)
//        val listFavorite: List<String> = arrayListOf(String())
//        Mockito.`when`(user.favorite).thenReturn(listFavorite)
//        Mockito.`when`(repositoryInterface.updateUser(user, repository, repository))
//            .then { repository.onSuccess(void) }
//        val liveData = repository.deleteVendorFavorite("8745811286", user)
//        Assert.assertEquals(liveData.value!!.status, true)
//    }
//
//    @Test
//    fun signOut() {
//    }
//
//    @Test
//    fun getLoading() {
//        val liveData = repository.getLoading()
//        Assert.assertNotNull(liveData)
//    }
}