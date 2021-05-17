Vesão da Lib - 
tag = v1.0.0

implementação:

Adicionar no Gradle (Project)

allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  
  Implementar no Gradle (app)
  
  dependencies {
	        implementation 'com.github.raphaeloliver:Gazeustest:Tag'
	}
  
  
  Step 1. add the Maven -
  
  <repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
  
  Step 2. Add the dependency
  
  <dependency>
	    <groupId>com.github.raphaeloliver</groupId>
	    <artifactId>Gazeustest</artifactId>
	    <version>Tag</version>
	</dependency>
  
  
  ----------------------------------
  init Koin
  
  fun Init.startKoin() {
    KoinInstance.KoinContext.koinApplication = koinApplication {
        androidContext(getAppContext())
        val list = listOf(DataModule.ModuleLoad() , DomainModule.Domainload())
        modules(list)
    }
}

----------------------------------

start koin

startKoin {
            androidContext(applicationContext)
            modules(module {
                factory {  }
            })

            val listModules = listOf(AppModules.loadDomainModule())
            modules(listModules)
        }
  
  ----------------------------------
  
  
 Core
 
     private val userUseCase by inject<UseReposUseCase>()
    private val tagUseCase by inject<ReposTagUseCase>()

    fun getUserNameRepo(
        nameUser: String,
        callBack: (success: Boolean, data: List<UserRepos>?) -> Unit
    ) = userUseCase.getRepositoryUser(nameUser, callBack)

    fun getTagRepo(owner: String,
                   repo : String,
                   callBack: (success: Boolean, data: List<ReposTag>?) -> Unit
    ) = tagUseCase.getTagRepoOwner(owner,repo, callBack)

  
  ----------------------------------
  
  Repository User -
  
  class RepoUserRepository(private val coreProvider: Core) {

    fun getRepoUser(nameUser: String,
                    callback: (Boolean, List<UserRepos>?) -> Unit
                        ) = coreProvider.getUserNameRepo(nameUser, callback)
}

  ----------------------------------
Reposditory Tag

class RepoTagRepository(private val coreProvider: Core) {

    fun getRepoTagName(owner: String ,
                       repo: String,
                        callback: (Boolean, List<ReposTag>?) -> Unit
                        ) = coreProvider.getTagRepo(owner,repo, callback)
}