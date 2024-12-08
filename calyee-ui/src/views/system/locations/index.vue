<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="140px"
    >
      <el-form-item label="库位所在的仓库区域" prop="area">
        <el-input
          v-model="queryParams.area"
          placeholder="请输入库位所在的仓库区域"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="库位所在的巷道" prop="aisle">
        <el-input
          v-model="queryParams.aisle"
          placeholder="请输入库位所在的巷道，用于定位货架的通道"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="库位所在的排" prop="bay">
        <el-input
          v-model="queryParams.bay"
          placeholder="请输入库位所在的排"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="库位所在的层" prop="level">
        <el-input
          v-model="queryParams.level"
          placeholder="请输入库位所在的层"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <br />
      <el-form-item label="库位的具体位置" prop="position">
        <el-input
          v-model="queryParams.position"
          placeholder="请输入库位的具体位置"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:locations:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:locations:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:locations:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:locations:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="locationsList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        label="库位唯一标识符"
        align="center"
        prop="locationId"
      />
      <el-table-column label="库位所在的仓库区域" align="center" prop="area" />
      <el-table-column label="库位所在的巷道" align="center" prop="aisle" />
      <el-table-column label="库位所在的排" align="center" prop="bay" />
      <el-table-column label="库位所在的层" align="center" prop="level" />
      <el-table-column label="库位的具体位置" align="center" prop="position" />
      <el-table-column label="记录创建用户" align="center" prop="createUser" />
      <el-table-column label="记录更新用户" align="center" prop="updateUser" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            type="text"
            icon="el-icon-view"
            @click="handleShow(scope.row)"
            >当前库位下的产品</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:locations:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:locations:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改库位，记录仓库中每个具体存储位置的详细信息对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="open"
      width="1000px"
      append-to-body
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="库位所在的仓库区域" prop="area">
          <el-input
            v-model="form.area"
            placeholder="请输入库位所在的仓库区域"
          />
        </el-form-item>
        <el-form-item label="库位所在的巷道，用于定位货架的通道" prop="aisle">
          <el-input
            v-model="form.aisle"
            placeholder="请输入库位所在的巷道，用于定位货架的通道"
          />
        </el-form-item>
        <el-form-item label="库位所在的排" prop="bay">
          <el-input v-model="form.bay" placeholder="请输入库位所在的排" />
        </el-form-item>
        <el-form-item label="库位所在的层" prop="level">
          <el-input v-model="form.level" placeholder="请输入库位所在的层" />
        </el-form-item>
        <el-form-item label="库位的具体位置" prop="position">
          <el-input
            v-model="form.position"
            placeholder="请输入库位的具体位置"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog
      :title="title"
      :visible.sync="productsOpen"
      width="1000px"
      append-to-body
    >
      <el-form ref="form" :model="locationsProducts" label-width="120px">
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { listLocations, getLocations, delLocations, addLocations, updateLocations, getLocationsProducts } from "@/api/system/locations"

export default {
  name: "Locations",
  data () {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 库位，记录仓库中每个具体存储位置的详细信息表格数据
      locationsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        area: null,
        aisle: null,
        bay: null,
        level: null,
        position: null,
        createUser: null,
        updateUser: null,
        deleteFlag: null
      },
      // 表单参数
      form: {},
      // 当前库位下的产品列表
      locationsProducts:[],
      productsOpen: false,
      // 表单校验
      rules: {
        area: [
          { required: true, message: "库位所在的仓库区域不能为空", trigger: "blur" }
        ],
        aisle: [
          { required: true, message: "库位所在的巷道，用于定位货架的通道不能为空", trigger: "blur" }
        ],
        bay: [
          { required: true, message: "库位所在的排不能为空", trigger: "blur" }
        ],
        level: [
          { required: true, message: "库位所在的层不能为空", trigger: "blur" }
        ],
        position: [
          { required: true, message: "库位的具体位置不能为空", trigger: "blur" }
        ],
        createUser: [
          { required: true, message: "记录创建用户不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "记录创建时间不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created () {
    this.getList()
  },
  methods: {
    /** 查询库位，记录仓库中每个具体存储位置的详细信息列表 */
    getList () {
      this.loading = true
      listLocations(this.queryParams).then(response => {
        this.locationsList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel () {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset () {
      this.form = {
        locationId: null,
        area: null,
        aisle: null,
        bay: null,
        level: null,
        position: null,
        createUser: null,
        createTime: null,
        updateUser: null,
        updateTime: null,
        deleteFlag: null
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery () {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery () {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange (selection) {
      this.ids = selection.map(item => item.locationId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.reset()
      this.open = true
      this.title = "添加库位，记录仓库中每个具体存储位置的详细信息"
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset()
      const locationId = row.locationId || this.ids
      getLocations(locationId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改库位，记录仓库中每个具体存储位置的详细信息"
      })
    },
    /** 查看按钮操作 */
    handleShow (row) {
      this.locationsProducts = {}
      const locationId = row.locationId || this.ids
      getLocationsProducts(locationId).then(response => {
        this.locationsProducts = response.data
        this.productsOpen = true
      })
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.locationId != null) {
            updateLocations(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addLocations(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete (row) {
      const locationIds = row.locationId || this.ids
      this.$modal.confirm('是否确认删除库位，记录仓库中每个具体存储位置的详细信息编号为"' + locationIds + '"的数据项？').then(function () {
        return delLocations(locationIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => { })
    },
    /** 导出按钮操作 */
    handleExport () {
      this.download('system/locations/export', {
        ...this.queryParams
      }, `locations_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
