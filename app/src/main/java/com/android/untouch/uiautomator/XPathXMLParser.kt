package com.android.untouch.uiautomator

import android.os.Build
import androidx.annotation.RequiresApi
import com.android.untouch.utils.L
import com.blankj.utilcode.util.FileIOUtils
import org.w3c.dom.Document
import org.w3c.dom.NamedNodeMap
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.ByteArrayInputStream
import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPath
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

object XPathXMLParser {

    // 创建XPath处理器
    val xpath = XPathFactory.newInstance().newXPath()


    @RequiresApi(Build.VERSION_CODES.O)
    fun parserDoc(xmlPath: String): Document{
        val data = FileIOUtils.readFile2String(xmlPath)
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        val doc = builder.parse(ByteArrayInputStream(data.toByteArray()))
        return doc
    }

    private fun demo1(xpath: XPath,doc: Document){
        // 示例1：通过resource-id精确查找
        val resourceId = "com.tencent.mobileqq:id/ba3"
        val node = xpath.evaluate(
            "//node[@resource-id='$resourceId']",
            doc,
            XPathConstants.NODE
        ) as Node
        printNodeInfo(node)
    }

    private fun demo2(xpath: XPath,doc: Document){
        // 示例2：查找所有可点击元素
        val clickAbles = xpath.evaluate(
            "//node[@clickable='true']",
            doc,
            XPathConstants.NODESET
        ) as NodeList
        L.d(
            """可点击元素数量: ${clickAbles.length}""".trimIndent()
        )
    }

    private fun demo3(xpath: XPath,doc: Document){
        // 示例3：组合条件查询（搜索框+可点击）
        val searchBox = xpath.evaluate(
            "//node[@resource-id='com.tencent.mobileqq:id/336' and @clickable='true']",
            doc,
            XPathConstants.NODE
        ) as Node
        L.d(
            """搜索框坐标: ${searchBox.attributes.getNamedItem("bounds")}""".trimIndent()
        )
    }

    private fun printNodeInfo(node: Node?) {
        if (node != null) {
            val attrs = node.attributes
            L.d("找到节点: ")
            L.d("Class: " + getAttr(attrs, "class"))
            L.d("Text: " + getAttr(attrs, "text"))
            L.d("Bounds: " + getAttr(attrs, "bounds"))
        }
    }

    private fun getAttr(attrs: NamedNodeMap, name: String): String {
        val attr = attrs.getNamedItem(name)
        return if (attr != null) attr.nodeValue else ""
    }
}