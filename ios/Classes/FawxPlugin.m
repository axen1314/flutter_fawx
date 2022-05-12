#import "FawxPlugin.h"
#if __has_include(<fawx/fawx-Swift.h>)
#import <fawx/fawx-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "fawx-Swift.h"
#endif

@implementation FawxPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFawxPlugin registerWithRegistrar:registrar];
}
@end
