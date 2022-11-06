package service

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util
import java.util.Optional
import java.util.regex.Matcher
import java.util.regex.Pattern

class RegexFinder(var text: String) {
  // It can be while loop instead of if
  def findEmail():Option[String] = {
    val regex = "[a-zA-Z0-9][a-zA-Z0-9_.]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+"
    val pattern = Pattern.compile(regex)
    val matcher = pattern.matcher(text)
    if (matcher.find) Option(matcher.group)
    else Option.empty
  }

  def findName():Option[String] = {
    val keyword1 = " i am"
    val keyword2 = "my name is"
    val keyword3 = "my name’s"
    val keywords = Seq(keyword1,keyword2,keyword3)
    var name = null
    val text1 = text.toLowerCase

    for (keyword <- keywords) {
      val i = text1.indexOf(keyword)
      if (i > -1) {
        val startName = i + keyword.length + 1
        val endName = text1.indexOf(" ", i + keyword.length + 2)
        val startSurname = endName + 1
        val endSurname = text1.indexOf(" ", startSurname)
        val name = text.substring(startName, endName)
        return Option(name)

      }
    }
    Option(name)
  }

  def findSurname: Optional[String] = {
    val keyword1 = " i am "
    val keyword2 = "my name is"
    val keyword3 = "my name’s"
    val keywords = Seq(keyword1,keyword2,keyword3)
    val text1 = text.toLowerCase

    for (keyword <- keywords) {
      val i = text1.indexOf(keyword)
      if (i > -1) {
        val startName = i + keyword.length + 1
        val endName = text1.indexOf(" ", i + keyword.length + 2)
        val startSurname = endName + 1
        val endSurname = text1.indexOf(" ", startSurname)
        return Optional.of(text.substring(startSurname, endSurname))
      }
    }
    Option.empty
  }

  def findIdentityNo = {
    val regex = "[1-9]\\d{9}[02468]"
    val pattern = Pattern.compile(regex)
    val matcher = pattern.matcher(text)
    if (matcher.find) Optional.of(matcher.group)
    else Option.empty
  }

  def findBirthDate = {
    val regex = "\\d{1,2}/\\d{1,2}/\\d{4}"
    val pattern = Pattern.compile(regex)
    val matcher = pattern.matcher(text)
    if (matcher.find) Optional.of(LocalDate.parse(matcher.group, DateTimeFormatter.ofPattern("d/M/yyyy")))
    else Option.empty
  }

  def findPhoneNumber = {
    val regex = "(05)([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})+"
    val pattern = Pattern.compile(regex)
    val matcher = pattern.matcher(text)
    if (matcher.find) Option(matcher.group)
    else findHouseNumber
  }

  private def findHouseNumber = {
    val regex = "/^(0)([2348]{1})([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$/"
    val pattern = Pattern.compile(regex)
    val matcher = pattern.matcher(text)
    if (matcher.find) Option(matcher.group)
    else Option.empty
  }
}