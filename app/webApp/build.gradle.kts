import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    js {
        browser()
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":app:shared"))

            implementation(libs.compose.ui)
        }
    }
}

// Automatically patch webApp.js in all distribution and webpack output directories
// to guard Safari WebKit against Skiko C++ SkShader null pointer returns.
fun patchSkikoShaderInJs(dir: java.io.File) {
    val jsFile = dir.resolve("webApp.js")
    if (jsFile.exists()) {
        var text = jsFile.readText()
        val target = "\"./skiko.mjs\":a"
        val replacement = "\"./skiko.mjs\":(function(t){return new Proxy(t,{get:(o,p)=>{var f=o[p];if(typeof f===\"function\"){return function(){var r=f.apply(this,arguments);if((r===0||r===0n)&&p.startsWith(\"org_jetbrains_skia_Shader__1nMake\")&&p!==\"org_jetbrains_skia_Shader__1nMakeEmpty\"){if(o[\"org_jetbrains_skia_Shader__1nMakeEmpty\"]){return o[\"org_jetbrains_skia_Shader__1nMakeEmpty\"]();}}return r;};}return f;}});})(a)"
        if (text.contains(target)) {
            text = text.replace(target, replacement)
            jsFile.writeText(text)
            println("[Gradle] Successfully patched $jsFile with Safari Skiko nullptr shader guard.")
        }
    }
}

tasks.matching { it.name in listOf("wasmJsBrowserProductionWebpack", "wasmJsBrowserDistribution") }.configureEach {
    doLast {
        patchSkikoShaderInJs(layout.buildDirectory.dir("kotlin-webpack/wasmJs/productionExecutable").get().asFile)
        patchSkikoShaderInJs(layout.buildDirectory.dir("dist/wasmJs/productionExecutable").get().asFile)
    }
}
