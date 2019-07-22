import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { TestProjectSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [TestProjectSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [TestProjectSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TestProjectSharedModule {
  static forRoot() {
    return {
      ngModule: TestProjectSharedModule
    };
  }
}
