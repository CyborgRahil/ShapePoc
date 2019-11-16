package poc.com.rect.testUtility


import poc.com.rect.data.local.RectEntity

open class TestUtililty {
  companion object {


      fun createEntity(): RectEntity {
          val entity = RectEntity()
          entity.rectId = 0
          entity.coordinate = "Sample"

          return entity

      }


  }


}
